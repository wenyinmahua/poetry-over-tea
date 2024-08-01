package com.mahua.poetryovertea.service;

import com.mahua.poetryovertea.common.DeleteRequest;
import com.mahua.poetryovertea.model.dto.PoemDTO;
import com.mahua.poetryovertea.model.entity.Poem;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author mahua
* @description 针对表【poem(存储古诗详细信息)】的数据库操作Service
* @createDate 2024-08-01 14:25:29
*/
public interface PoemService extends IService<Poem> {

	Boolean addPoemBulk(MultipartFile file);

	Boolean deletePoem(DeleteRequest request);

	Boolean updatePoem(PoemDTO poemDTO);
}
