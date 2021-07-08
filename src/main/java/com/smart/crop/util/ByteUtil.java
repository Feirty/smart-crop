package com.smart.crop.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteUtil {
    /**
     * 字节 转换为B MB GB
     * @param size 字节大小
     * @return
     */
    public static String getPrintSize(long size){
        long rest = 0;
        if(size < 1024){
            return String.valueOf(size) + "B";
        }else{
            size /= 1024;
        }

        if(size < 1024){
            return String.valueOf(size) + "KB";
        }else{
            rest = size % 1024;
            size /= 1024;
        }

        if(size < 1024){
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((rest * 100 / 1024 % 100)) + "MB";
        }else{
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    /**
     * InputStream转字节
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] readBytes(InputStream in) throws IOException {
        byte[] temp = new byte[in.available()];
        byte[] result = new byte[0];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            byte[] readBytes = new byte[size];
            System.arraycopy(temp, 0, readBytes, 0, size);
            result = mergeArray(result,readBytes);
        }
        return result;
    }

    /***
     * 合并字节数组
     * @param a
     * @return
     */
    public static byte[] mergeArray(byte[]... a) {
        // 合并完之后数组的总长度
        int index = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i].length;
        }
        byte[] result = new byte[sum];
        for (int i = 0; i < a.length; i++) {
            int lengthOne = a[i].length;
            if(lengthOne==0){
                continue;
            }
            // 拷贝数组
            System.arraycopy(a[i], 0, result, index, lengthOne);
            index = index + lengthOne;
        }
        return result;
    }


    public static BufferedImage readByteBufferToBufferedImage(ByteBuffer byteBuffer) throws IOException
    {

        if(null==byteBuffer)
            return null;

        byte [] b = byteBuffer.array();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        BufferedImage image = ImageIO.read(in);

        return image;
    }
}
