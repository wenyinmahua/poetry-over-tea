package com.mahua.poetryovertea.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mahua.poetryovertea.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 存储古诗详细信息
 * @TableName poem
 */
@TableName(value ="poem")
@Data
public class PoemQueryDTO extends PageRequest implements Serializable {
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
     * 诗人名
     */
    private String poet;

    /**
     * 创作朝代
     */
    private String dynasty;

    /**
     * 古诗正文内容
     */
    private String content;

    /**
     * 古诗分类
     */
    private String category;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}