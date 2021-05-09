package com.kingoin.king.modular.biz.service;

import com.kingoin.king.modular.biz.dto.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

    Result uploadFile(MultipartFile file, String prefix) throws Exception;
}
