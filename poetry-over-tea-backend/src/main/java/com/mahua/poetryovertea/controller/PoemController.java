package com.mahua.poetryovertea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mahua.poetryovertea.annotation.AuthCheck;
import com.mahua.poetryovertea.common.BaseResponse;
import com.mahua.poetryovertea.common.DeleteRequest;
import com.mahua.poetryovertea.common.ResultUtils;
import com.mahua.poetryovertea.constant.UserConstant;
import com.mahua.poetryovertea.model.dto.PoemDTO;
import com.mahua.poetryovertea.model.dto.PoemQueryTagDTO;
import com.mahua.poetryovertea.model.vo.PoemVO;
import com.mahua.poetryovertea.service.PoemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/poem")
public class PoemController {


	@Resource
	private PoemService poemService;

	// region 古诗词增删改查

	@GetMapping("/page")
	public BaseResponse<Page<PoemVO>> getPoemsByPage(PoemQueryTagDTO poemQueryTagDTO){
		return ResultUtils.success(poemService.getPoemsByPage(poemQueryTagDTO));
	}

	@PostMapping("/update")
	public BaseResponse<Boolean> updatePoem(PoemDTO poemDTO){
		return ResultUtils.success(poemService.updatePoem(poemDTO));
	}

	@PostMapping("/add/bulk")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Boolean> addPoemBulk(@RequestBody MultipartFile file){
		return ResultUtils.success(poemService.addPoemBulk(file));
	}

	@PostMapping("/delete")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Boolean> deletePoem(@RequestBody DeleteRequest request){
		return ResultUtils.success(poemService.deletePoem(request));
	}





	// endregion

}
