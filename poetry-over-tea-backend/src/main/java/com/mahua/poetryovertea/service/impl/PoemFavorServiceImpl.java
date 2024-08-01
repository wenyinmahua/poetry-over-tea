package com.mahua.poetryovertea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.mapper.PoemFavorMapper;
import com.mahua.poetryovertea.model.entity.PoemFavor;
import com.mahua.poetryovertea.service.PoemFavorService;
import org.springframework.stereotype.Service;

/**
* @author mahua
* @description 针对表【poem_favor(存储用户-古诗收藏基本信息)】的数据库操作Service实现
* @createDate 2024-08-01 14:46:34
*/
@Service
public class PoemFavorServiceImpl extends ServiceImpl<PoemFavorMapper, PoemFavor>
    implements PoemFavorService {

}




