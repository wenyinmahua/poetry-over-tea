package com.mahua.poetryovertea.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储作者基本信息
 * @TableName poet
 */
@TableName(value ="poet")
@Data
public class Poet implements Serializable {
    /**
     * 诗人 ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 诗人画像 URL
     */
    private String avatarUrl;

    /**
     * 诗人姓名
     */
    private String name;

    /**
     * 诗人朝代 Id
     */
    private Long dynastyId;

    /**
     * 诗人简介
     */
    private String profile;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}