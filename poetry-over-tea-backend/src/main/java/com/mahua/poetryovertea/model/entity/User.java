package com.mahua.poetryovertea.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 存储用户基本信息
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
	/**
	 * 用户ID，自增主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户昵称
	 */
	private String username;

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 用户头像URL
	 */
	private String avatarUrl;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户简历
	 */
	private String profile;

	/**
	 * 用户角色，0-普通用户 1-管理员
	 */
	private Integer role;

	/**
	 * 用户状态，0-正常，1-禁用
	 */
	private Integer status;

	/**
	 * 账户创建时间
	 */
	private Date createdTime;

	/**
	 * 信息最后更新时间
	 */
	private Date updatedTime;

	/**
	 * 是否删除 0 - 未删除， 1 - 删除
	 */
	private Integer isDelete;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}