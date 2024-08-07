package com.mahua.poetryovertea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mahua.poetryovertea.common.ErrorCode;
import com.mahua.poetryovertea.constant.UserConstant;
import com.mahua.poetryovertea.exception.BusinessException;
import com.mahua.poetryovertea.exception.ThrowUtils;
import com.mahua.poetryovertea.mapper.UserMapper;
import com.mahua.poetryovertea.mapper.User;
import com.mahua.poetryovertea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 用户服务实现类
 *
 * @author mahua
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
		implements UserService {

	@Resource
	private UserMapper userMapper;

	/**
	 * 盐值，混淆密码
	 */
	private static final String SALT = "mahua";

	@Override
	public long userRegister(String account, String password, String checkPassword) {
		// 1. 校验
		if (StringUtils.isAnyBlank(account, password, checkPassword)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
		}
		if (account.length() < 4) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
		}
		if (password.length() < 8 || checkPassword.length() < 8) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
		}
		// 密码和校验密码相同
		if (!password.equals(checkPassword)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
		}
		// 账户不能重复
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("account", account);
		long count = userMapper.selectCount(queryWrapper);
		if (count > 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
		}
		// 2. 加密
		String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
		// 3. 插入数据
		User user = new User();
		user.setAccount(account);
		user.setPassword(encryptPassword);
		boolean saveResult = this.save(user);
		if (!saveResult) {
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
		}
		return user.getId();

	}

	@Override
	public User userLogin(String account, String password, HttpServletRequest request) {
		// 1. 校验
		if (StringUtils.isAnyBlank(account, password)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
		}
		if (account.length() < 4) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
		}
		if (password.length() < 5) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
		}
		// 2. 加密
		String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
		// 查询用户是否存在
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("account", account);
		queryWrapper.eq("password", encryptPassword);
		User user = userMapper.selectOne(queryWrapper);
		// 用户不存在
		if (user == null) {
			log.info("user login failed, account cannot match password");
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
		}
		// 3. 记录用户的登录态
		request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
		return user;
	}

	/**
	 * 获取当前登录用户
	 *
	 * @param request
	 * @return
	 */
	@Override
	public User getLoginUser(HttpServletRequest request) {
		// 先判断是否已登录
		Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
		User currentUser = (User) userObj;
		if (currentUser == null || currentUser.getId() == null) {
			throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
		}
		// 从数据库查询（追求性能的话可以注释，直接走缓存）
		long userId = currentUser.getId();
		currentUser = this.getById(userId);
		ThrowUtils.throwIf(currentUser == null, ErrorCode.NOT_LOGIN_ERROR);
		return currentUser;
	}

	/**
	 * 是否为管理员
	 *
	 * @param request
	 * @return
	 */
	@Override
	public boolean isAdmin(HttpServletRequest request) {
		// 仅管理员可查询
		Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
		User user = (User) userObj;
		return user != null && UserConstant.ADMIN_ROLE_CODE.equals(user.getRole());
	}

	/**
	 * 用户注销
	 *
	 * @param request
	 */
	@Override
	public boolean userLogout(HttpServletRequest request) {
		if (request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null) {
			throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
		}
		// 移除登录态
		request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
		return true;
	}

}
