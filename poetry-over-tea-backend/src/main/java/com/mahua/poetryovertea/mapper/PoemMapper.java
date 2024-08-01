package com.mahua.poetryovertea.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mahua.poetryovertea.model.entity.Poem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mahua.poetryovertea.model.vo.PoemVO;
import org.apache.ibatis.annotations.Param;

/**
* @author mahua
* @description 针对表【poem(存储古诗详细信息)】的数据库操作Mapper
* @createDate 2024-08-01 14:25:29
* @Entity com.mahua.poetryovertea.model.entity.Poem
*/
public interface PoemMapper extends BaseMapper<Poem> {

	Page<PoemVO> queryPoemsByPoetId(@Param("page") IPage<PoemVO> page, @Param("poetId") Long poetId);

	Page<PoemVO> queryPoemsByDynastyId(@Param("page") IPage<PoemVO> page, @Param("dynastyId") Long dynastyId);

	Page<PoemVO> queryPoemsByCategoryId(Page<PoemVO> poemVOPage, @Param("categoryId") Long categoryId);

	Page<PoemVO> queryPoems(Page<PoemVO> poemVOPage);
}




