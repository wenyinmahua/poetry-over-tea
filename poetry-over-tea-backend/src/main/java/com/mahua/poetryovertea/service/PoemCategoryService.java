package com.mahua.poetryovertea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mahua.poetryovertea.model.entity.PoemCategory;

/**
* @author mahua
* @description 针对表【category(存储诗词分类基本信息)】的数据库操作Service
* @createDate 2024-08-01 14:46:13
*/
public interface PoemCategoryService extends IService<PoemCategory> {

	Long getCategoryIdByName(String categoryName);
}
