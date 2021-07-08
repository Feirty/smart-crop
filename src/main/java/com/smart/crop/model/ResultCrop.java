package com.smart.crop.model;

import lombok.Data;

@Data
public class ResultCrop {

    private Crop crop;

    private Image image;

    @Data
    public static class Crop {
        public int x, y, width, height;

        public Crop(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    @Data
    public static class Image {
        public int w;
        public int h;

        public Image(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }
}
