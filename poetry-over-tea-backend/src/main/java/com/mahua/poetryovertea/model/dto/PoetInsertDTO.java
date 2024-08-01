package com.mahua.poetryovertea.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 诗人 Excel 插入数据结构
 */
@Data
public class PoetInsertDTO implements Serializable {
    /**
     * 诗人画像 URL
     */
    private String avatarUrl;

    /**
     * 诗人姓名
     */
    private String name;

    /**
     * 诗人朝代
     */
    private String dynasty;

    /**
     * 诗人简介
     */
    private String profile;

}