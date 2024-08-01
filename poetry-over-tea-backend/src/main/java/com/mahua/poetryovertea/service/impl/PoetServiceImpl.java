package com.mahua.poetryovertea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.model.entity.Poet;
import com.mahua.poetryovertea.service.PoetService;
import com.mahua.poetryovertea.mapper.PoetMapper;
import org.springframework.stereotype.Service;

/**
* @author mahua
* @description 针对表【poet(存储作者基本信息)】的数据库操作Service实现
* @createDate 2024-08-01 14:44:48
*/
@Service
public class PoetServiceImpl extends ServiceImpl<PoetMapper, Poet>
    implements PoetService{

}




