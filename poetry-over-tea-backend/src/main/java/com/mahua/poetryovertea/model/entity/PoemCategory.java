package com.mahua.poetryovertea.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储诗词分类基本信息
 * @TableName category
 */
@TableName(value ="category")
@Data
public class PoemCategory implements Serializable {
    /**
     * 分类ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}