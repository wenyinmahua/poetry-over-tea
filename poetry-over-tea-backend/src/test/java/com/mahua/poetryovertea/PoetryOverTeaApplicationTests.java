package com.mahua.poetryovertea;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mahua.poetryovertea.mapper.PoemMapper;
import com.mahua.poetryovertea.model.dto.PoemQueryTagDTO;
import com.mahua.poetryovertea.model.vo.PoemVO;
import com.mahua.poetryovertea.service.PoemService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class PoetryOverTeaApplicationTests {

	@Resource
	private PoemService poemService;

	@Test
	void contextLoads() {
		PoemQueryTagDTO poemQueryTagDTO = new PoemQueryTagDTO();
//		poemQueryTagDTO.setPoet("李白");
//		poemQueryTagDTO.setDynasty("宋");
//		poemQueryTagDTO.setCategory("抒情诗");
		poemQueryTagDTO.setCurrent(1);
		poemQueryTagDTO.setPageSize(3);

		IPage<PoemVO> poemVOS = poemService.getPoemsByPage(poemQueryTagDTO);
		List<PoemVO> records = poemVOS.getRecords();
		for (PoemVO poemVO:records){
			System.out.println(poemVO);
		}
	}

}
