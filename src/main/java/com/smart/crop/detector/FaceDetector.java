package com.smart.crop.detector;

import java.awt.image.BufferedImage;

public interface FaceDetector {

    void detect(BufferedImage input, FaceConsumer consumer);

    @FunctionalInterface
    interface FaceConsumer {
        void accept(int x, int y, int width, int height, float confidence);
    }

}
