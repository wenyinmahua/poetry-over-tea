package com.mahua.poetryovertea.controller;


import com.mahua.poetryovertea.annotation.AuthCheck;
import com.mahua.poetryovertea.common.BaseResponse;
import com.mahua.poetryovertea.common.ResultUtils;
import com.mahua.poetryovertea.constant.UserConstant;
import com.mahua.poetryovertea.service.PoetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/poet")
public class PoetController {

	@Resource
	private PoetService poetService;

	@PostMapping("/add/bulk")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Boolean> addPoetBulk(@RequestBody MultipartFile file){
		return ResultUtils.success(poetService.addPoetBulk(file));
	}
}
