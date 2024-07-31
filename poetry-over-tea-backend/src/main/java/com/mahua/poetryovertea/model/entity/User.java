package com.mahua.poetryovertea.model.entity;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
	/**
	 * 用户id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户昵称
	 */
	private String username;

	/**
	 * 账号
	 */
	private String userAccount;

	/**
	 * 密码
	 */
	private String userPassword;

	/**
	 * 状态：0-正常，1-禁用
	 */
	private Integer userStatus;

	/**
	 * 用户角色：
	 * 0-普通用户
	 * 1-管理员
	 * 2-超级管理员
	 */
	private Integer userRole;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updateTime;

	/**
	 * 是否删除
	 */
	@TableLogic
	private Integer isDelete;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}