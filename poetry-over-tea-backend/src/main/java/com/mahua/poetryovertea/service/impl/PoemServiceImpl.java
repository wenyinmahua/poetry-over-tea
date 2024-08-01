package com.mahua.poetryovertea.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.common.DeleteRequest;
import com.mahua.poetryovertea.common.ErrorCode;
import com.mahua.poetryovertea.exception.BusinessException;
import com.mahua.poetryovertea.model.dto.PoemDTO;
import com.mahua.poetryovertea.model.dto.PoemInsertDTO;
import com.mahua.poetryovertea.model.dto.PoemQueryTagDTO;
import com.mahua.poetryovertea.model.entity.Dynasty;
import com.mahua.poetryovertea.model.entity.Poem;
import com.mahua.poetryovertea.model.entity.PoemCategory;
import com.mahua.poetryovertea.model.entity.Poet;
import com.mahua.poetryovertea.model.vo.PoemVO;
import com.mahua.poetryovertea.service.PoemCategoryService;
import com.mahua.poetryovertea.service.DynastyService;
import com.mahua.poetryovertea.service.PoemService;
import com.mahua.poetryovertea.mapper.PoemMapper;
import com.mahua.poetryovertea.service.PoetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

/**
* @author mahua
* @description 针对表【poem(存储古诗详细信息)】的数据库操作Service实现
* @createDate 2024-08-01 14:25:29
*/
@Service
public class PoemServiceImpl extends ServiceImpl<PoemMapper, Poem>
    implements PoemService{

	@Resource
	private PoetService poetService;

	@Resource
	private DynastyService dynastyService;

	@Resource
	private PoemCategoryService poemCategoryService;

	@Resource
	private PoemMapper poemMapper;

	@Override
	public Page<PoemVO> getPoemsByPage(PoemQueryTagDTO poemQueryTagDTO) {

		// 假设只能按一个条件来查。
		String poetName = poemQueryTagDTO.getPoet();
		String dynastyName = poemQueryTagDTO.getDynasty();
		String categoryName = poemQueryTagDTO.getCategory();

		int current = poemQueryTagDTO.getCurrent();
		int pageSize = poemQueryTagDTO.getPageSize();
		Page<PoemVO> poemVOPage = new Page<>(current,pageSize);
		if (StringUtils.isNotEmpty(poetName)){
			// 诗人名不为空，可以确定朝代了，两个都确定了，这个时候只需要联表查询分类了，直接将查出的古诗写入字符串值。
			Poet poet = poetService.getOne(new QueryWrapper<Poet>().eq("name",poetName));
			Long poetId = poet.getId();
			dynastyName = dynastyService.getOne(new QueryWrapper<Dynasty>().eq("id",poet.getDynastyId())).getName();
			String finalDynastyName = dynastyName;
			poemVOPage = poemMapper.queryPoemsByPoetId(poemVOPage,poetId);
			poemVOPage.getRecords().stream().map(poemVO -> {
				poemVO.setDynasty(finalDynastyName);
				poemVO.setPoet(poetName);
				return poemVO;
			}).collect(Collectors.toList());
			return poemVOPage;
		}
		if (StringUtils.isNotEmpty(dynastyName)){
			// 朝代确定了，只能先查诗人 id ，然后再查古诗表+分类表了。
			Long dynastyId = dynastyService.getOne(new QueryWrapper<Dynasty>().eq("name",dynastyName)).getId();
			poemVOPage = poemMapper.queryPoemsByDynastyId(poemVOPage,dynastyId);
			String finalDynastyName = dynastyName;
			poemVOPage.getRecords().stream().peek(poemVO -> poemVO.setDynasty(finalDynastyName)).collect(Collectors.toList());
			return poemVOPage;
		}
		if (StringUtils.isNotEmpty(categoryName)){
			// 分类确定了，确定查什么诗了，作者也知道了，当然朝代也知道了。
			Long categoryId = poemCategoryService.getOne(new QueryWrapper<PoemCategory>().eq("name",categoryName)).getId();
			poemVOPage = poemMapper.queryPoemsByCategoryId(poemVOPage,categoryId);
			poemVOPage.getRecords().stream().peek(poemVO -> poemVO.setCategory(categoryName));
			return poemVOPage;
		}
		poemVOPage = poemMapper.queryPoems(poemVOPage);
		return poemVOPage;

	}

	@Override
	@Transactional
	public Boolean addPoemBulk(MultipartFile file) {
		try {
			if (file == null) {
				throw new BusinessException(ErrorCode.PARAMS_ERROR, "请选择上传文件");
			}
			String originalFilename = file.getOriginalFilename();
			if (originalFilename == null || (!originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx"))) {
				throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件格式错误");
			}
			// 使用try-with-resources自动关闭InputStream，防止资源泄露
			try (InputStream inputStream = file.getInputStream()) {
				EasyExcel.read(inputStream, PoemInsertDTO.class, new PageReadListener<PoemInsertDTO>(poemDTOList -> {
					for (PoemInsertDTO poemInsertDTO : poemDTOList) {
						Poem poem = new Poem();
						BeanUtils.copyProperties(poemInsertDTO, poem);
						Long poetId = poetService.getPoetIdByName(poemInsertDTO.getPoet());
						poem.setPoetId(poetId);
						Long categoryId = poemCategoryService.getCategoryIdByName(poemInsertDTO.getCategory());
						poem.setCategoryId(categoryId);
						this.save(poem);
					}
				})).sheet().doRead();
			}
			return true;
		} catch (IOException e) {
			// 处理解析文件时可能发生的IO异常
			log.error("读取Excel文件时发生错误", e);
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件读取失败");
		}
	}

	@Override
	public Boolean deletePoem(DeleteRequest request) {
		if (request == null){
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		}
		Long id = request.getId();
		if (id < 0){
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		return this.removeById(id);
	}

	@Override
	@Transactional
	public Boolean updatePoem(PoemDTO poemDTO) {
		String poet = poemDTO.getPoet();
		String dynasty = poemDTO.getDynasty();
		String category = poemDTO.getCategory();
		Poem poem = new Poem();
		BeanUtils.copyProperties(poemDTO,poem);
		if (StringUtils.isNotEmpty(category)){
			Long categoryId = poemCategoryService.getOne(new QueryWrapper<PoemCategory>().eq("name",category)).getId();
			if (categoryId == null){
				PoemCategory poemCategory = new PoemCategory();
				poemCategory.setName(category);
				poemCategoryService.save(poemCategory);
				categoryId = poemCategory.getId();
			}
			poem.setCategoryId(categoryId);
		}
		if (StringUtils.isNotEmpty(poet)){
			Long poetId = poetService.getOne(new QueryWrapper<Poet>().eq("name",poet)).getId();
			if (poetId == null){
				Poet insertPoet = new Poet();
				insertPoet.setName(poet);
				Long dynastyId = dynastyService.getOne(new QueryWrapper<Dynasty>().eq("name",dynasty)).getId();
				if (dynastyId == null){
					Dynasty insertDynasty = new Dynasty();
					insertDynasty.setName(dynasty);
					dynastyService.save(insertDynasty);
					dynastyId = insertDynasty.getId();
				}
				insertPoet.setDynastyId(dynastyId);
				poetService.save(insertPoet);
				poetId = insertPoet.getId();
			}
			poem.setPoetId(poetId);
		}
		return this.updateById(poem);
	}
}




