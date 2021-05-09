package com.kingoin.king.modular.biz.service.impl;


import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.modular.biz.dto.Result;
import com.kingoin.king.modular.biz.service.UploadFileService;
import com.kingoin.king.modular.biz.util.SysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class UploadFileServiceImpl implements UploadFileService {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);



    @Override
    public Result uploadFile(MultipartFile file, String prefix) throws Exception {
        Result result = new Result();
        try {
        String pictureName = prefix+UUID.randomUUID().toString() + ".jpg";
        String fileUploadPath = "C:\\boden\\tomcat-8-80\\webapps\\images\\";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newPath = fileUploadPath+sdf.format(new Date())+"/";
        File uploadPath = new File(newPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        file.transferTo(new File(newPath + pictureName));
        String path = "https://www.boden-uk.com/images/"+sdf.format(new Date())+"/"+pictureName;
        Map<String,Object> map = new HashMap<>();
        map.put("path",path);
        result.setMap(map);
        } catch (Exception e) {
            logger.info(e.getMessage());
            SysUtils.setErrorMsg(result,"上传图片失败!");
        }
        return result;
    }
}