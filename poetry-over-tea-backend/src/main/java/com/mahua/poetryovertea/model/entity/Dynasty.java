package com.mahua.poetryovertea.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储诗人朝代基本信息
 * @TableName dynasty
 */
@TableName(value ="dynasty")
@Data
public class Dynasty implements Serializable {
    /**
     * 朝代ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 朝代名
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}