package com.smart.crop.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoCropDTO {
    /**
     * 图片url 必传
     */
    @NotBlank(message = "imageUrl is not null")
    private String imageUrl;
    /**
     * 宽高比 非必传
     */
    private String ratios;
    /**
     * 固定宽度 非必传
     */
    private Integer width;
    /**
     * 固定高度 非必传
     */
    private Integer height;
}
