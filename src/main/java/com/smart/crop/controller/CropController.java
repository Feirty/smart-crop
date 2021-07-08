package com.smart.crop.controller;

import com.smart.crop.model.DoCropDTO;
import com.smart.crop.model.ResultCrop;
import com.smart.crop.service.ImageCropService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CropController {

    @Resource
    private ImageCropService imageCropService;

    /**
     * 图片智能裁剪
     * @param dto
     * @return
     * @throws IOException
     */
    @PostMapping("crop")
    public Map<String,Object> crop(@Validated DoCropDTO dto) throws IOException {
        ResultCrop resultCrop = imageCropService.doCrop(dto);
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","处理完成");
        result.put("data",resultCrop);
        return result;
    }

}
