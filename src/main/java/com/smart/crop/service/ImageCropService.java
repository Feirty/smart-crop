package com.smart.crop.service;

import com.smart.crop.detector.DnnFaceDetector;
import com.smart.crop.detector.FaceDetector;
import com.smart.crop.model.DoCropDTO;
import com.smart.crop.model.ResultCrop;
import com.smart.crop.util.Boost;
import com.smart.crop.util.CropResult;
import com.smart.crop.util.Options;
import com.smart.crop.util.SmartCrop;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageCropService {

    private FaceDetector faceDetector = new DnnFaceDetector();

    /**
     * 智能裁剪
     * @param dto
     * @return
     * @throws IOException
     */
    public ResultCrop doCrop(DoCropDTO dto) throws IOException {
        Float ratios = StringUtils.isEmpty(dto.getRatios())? 1.0f:Float.parseFloat(dto.getRatios());
        BufferedImage input= ImageIO.read(new URL(dto.getImageUrl()));
        int w = input.getWidth();
        int h = input.getHeight();
        List<Boost> faces = new ArrayList<>();
        faceDetector.detect(input, (x, y, width, height, confidence) -> {
            Boost boost = new Boost();
            boost.x = x;
            boost.y = y;
            boost.width = width;
            boost.height = height;
            boost.weight = 1.0f;
            faces.add(boost);
        });
        Options options = new Options()
                .prescale(true)
                .boost(faces)
                .scoreDownSample(8)
                .ruleOfThirds(true);
        if(!StringUtils.isEmpty(dto.getRatios())){
            options.aspect(ratios);
        }
        if(null != dto.getWidth()){
            options.cropWidth(dto.getWidth());
        }
        if(null != dto.getHeight()){
            options.cropHeight(dto.getHeight());
        }
        SmartCrop smartCrop = SmartCrop.analyze(options, input);
        CropResult cropResult = smartCrop.generateCrops(options);
        ResultCrop resultCrop = new ResultCrop();
        ResultCrop.Image image = new ResultCrop.Image(w,h);
        ResultCrop.Crop crop = new ResultCrop.Crop(cropResult.topCrop.x,cropResult.topCrop.y,cropResult.topCrop.width,cropResult.topCrop.height);
        resultCrop.setCrop(crop);
        resultCrop.setImage(image);
        return resultCrop;
    }
}
