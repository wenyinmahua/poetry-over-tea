package com.mahua.poetryovertea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.mapper.DynastyMapper;
import com.mahua.poetryovertea.model.entity.Dynasty;
import com.mahua.poetryovertea.service.DynastyService;
import org.springframework.stereotype.Service;

/**
* @author mahua
* @description 针对表【dynasty(存储诗人朝代基本信息)】的数据库操作Service实现
* @createDate 2024-08-01 14:46:22
*/
@Service
public class DynastyServiceImpl extends ServiceImpl<DynastyMapper, Dynasty>
    implements DynastyService {

}




