package com.mahua.poetryovertea.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储古诗详细信息
 * @TableName poem
 */
@TableName(value ="poem")
@Data
public class Poem implements Serializable {
    /**
     * 古诗 ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 古诗题目
     */
    private String title;

    /**
     * 诗人 ID，关联 poet 表
     */
    private Long poetId;


    /**
     * 古诗正文内容
     */
    private String content;

    /**
     * 古诗分类 Id
     */
    private Long categoryId;

    /**
     * 古诗译文
     */
    private String translation;

    /**
     * 古诗赏析内容
     */
    private String appreciation;

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