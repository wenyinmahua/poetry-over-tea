package com.mahua.poetryovertea.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.common.ErrorCode;
import com.mahua.poetryovertea.exception.BusinessException;
import com.mahua.poetryovertea.model.dto.PoetInsertDTO;
import com.mahua.poetryovertea.model.entity.Poet;
import com.mahua.poetryovertea.service.DynastyService;
import com.mahua.poetryovertea.service.PoetService;
import com.mahua.poetryovertea.mapper.PoetMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
* @author mahua
* @description 针对表【poet(存储作者基本信息)】的数据库操作Service实现
* @createDate 2024-08-01 14:44:48
*/
@Service
public class PoetServiceImpl extends ServiceImpl<PoetMapper, Poet>
    implements PoetService{

	@Resource
	private DynastyService dynastyService;

	@Override
	public Long getPoetIdByName(String  poetName) {
		QueryWrapper<Poet> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name",poetName);
		Poet poem = this.getOne(queryWrapper);
		if (poem == null){
			Poet newPoet = new Poet();
			newPoet.setName(poetName);
			this.save(newPoet);
			return newPoet.getId();
		}
		return poem.getId();
	}

	@Override
	public Boolean addPoetBulk(MultipartFile file) {
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
				EasyExcel.read(inputStream, PoetInsertDTO.class, new PageReadListener<PoetInsertDTO>(poetInsertDTOS -> {
					for (PoetInsertDTO poetInsertDTO : poetInsertDTOS) {
						Poet poet = new Poet();
						BeanUtils.copyProperties(poetInsertDTO, poet);
						Long dynastyId = dynastyService.getDynastyIdByName(poetInsertDTO.getDynasty());
						poet.setDynastyId(dynastyId);
						try {
							this.save(poet);
						} catch (DuplicateKeyException e) {
							log.error("重复的名字:"+poet.getName());
						}
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
}




