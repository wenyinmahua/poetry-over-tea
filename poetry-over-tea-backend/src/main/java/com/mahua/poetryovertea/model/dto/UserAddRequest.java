package com.mahua.poetryovertea.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * @author mahua
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String account;


    /**
     * 用户角色: 0 - user, 1 - admin
     */
    private Integer role;

    /**
     * 密码
     */
    private String password;

    private static final long serialVersionUID = 1L;
}