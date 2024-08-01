package com.mahua.poetryovertea.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 *
 * @author mahua
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户角色: 0- user, 1 - admin
     */
    private Integer role;

    /**
     * 密码
     */
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}