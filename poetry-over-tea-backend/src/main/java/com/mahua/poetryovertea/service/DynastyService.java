package com.mahua.poetryovertea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mahua.poetryovertea.model.entity.Dynasty;

/**
* @author mahua
* @description 针对表【dynasty(存储诗人朝代基本信息)】的数据库操作Service
* @createDate 2024-08-01 14:46:22
*/
public interface DynastyService extends IService<Dynasty> {

	Long getDynastyIdByName(String dynasty);
}
