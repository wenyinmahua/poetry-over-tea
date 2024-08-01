package com.mahua.poetryovertea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mahua.poetryovertea.common.DeleteRequest;
import com.mahua.poetryovertea.model.dto.PoemDTO;
import com.mahua.poetryovertea.model.dto.PoemQueryTagDTO;
import com.mahua.poetryovertea.model.entity.Poem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mahua.poetryovertea.model.vo.PoemVO;
import org.springframework.web.multipart.MultipartFile;

/**
* @author mahua
* @description 针对表【poem(存储古诗详细信息)】的数据库操作Service
* @createDate 2024-08-01 14:25:29
*/
public interface PoemService extends IService<Poem> {

	Page<PoemVO> getPoemsByPage(PoemQueryTagDTO poemQueryTagDTOO);

	Boolean addPoemBulk(MultipartFile file);

	Boolean deletePoem(DeleteRequest request);

	Boolean updatePoem(PoemDTO poemDTO);
}
