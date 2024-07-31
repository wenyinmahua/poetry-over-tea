package com.mahua.poetryovertea.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mahua.poetryovertea.model.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
* @author mahua
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-12-31 14:58:40
* @Entity com.mahua.juanju.model.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




