package com.mahua.poetryovertea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.common.DeleteRequest;
import com.mahua.poetryovertea.common.ErrorCode;
import com.mahua.poetryovertea.exception.BusinessException;
import com.mahua.poetryovertea.model.dto.PoemDTO;
import com.mahua.poetryovertea.model.entity.Dynasty;
import com.mahua.poetryovertea.model.entity.Poem;
import com.mahua.poetryovertea.model.entity.PoemCategory;
import com.mahua.poetryovertea.model.entity.Poet;
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

	@Override
	public Boolean addPoemBulk(MultipartFile file) {
		// todo 插入古诗词实现
		return null;
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




