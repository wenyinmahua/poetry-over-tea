package com.mahua.poetryovertea.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author mahua
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String account;

    private String password;
}
