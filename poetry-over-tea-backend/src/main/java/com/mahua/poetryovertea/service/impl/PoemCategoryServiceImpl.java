package com.mahua.poetryovertea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.mapper.PoemCategoryMapper;
import com.mahua.poetryovertea.model.entity.PoemCategory;
import com.mahua.poetryovertea.service.PoemCategoryService;
import org.springframework.stereotype.Service;

/**
* @author mahua
* @description 针对表【category(存储诗词分类基本信息)】的数据库操作Service实现
* @createDate 2024-08-01 14:46:13
*/
@Service
public class PoemCategoryServiceImpl extends ServiceImpl<PoemCategoryMapper, PoemCategory>
    implements PoemCategoryService {

	@Override
	public Long getCategoryIdByName(String categoryName) {
		PoemCategory category = this.getOne(new QueryWrapper<PoemCategory>().eq("name",categoryName));
		if (category == null){
			category = new PoemCategory();
			category.setName(categoryName);
			this.save(category);
		}
		return category.getId();
	}
}




