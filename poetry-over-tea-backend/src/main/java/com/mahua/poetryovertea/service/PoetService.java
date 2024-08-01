package com.mahua.poetryovertea.service;

import com.mahua.poetryovertea.model.entity.Poet;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author mahua
* @description 针对表【poet(存储作者基本信息)】的数据库操作Service
* @createDate 2024-08-01 14:44:48
*/
public interface PoetService extends IService<Poet> {

	Long getPoetIdByName(String poet);

	Boolean addPoetBulk(MultipartFile file);
}
