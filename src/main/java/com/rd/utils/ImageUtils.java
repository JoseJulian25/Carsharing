package com.rd.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    public static List<byte[]> decompressImage(List<byte[]> datas){
        Inflater inflater = new Inflater();
        List<byte[]> bytes = new ArrayList<>();

        datas.forEach(data -> {
            inflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] tmp = new byte[4*1024];
            try{
                while(!inflater.finished()){
                    int count = inflater.inflate(tmp);
                    outputStream.write(tmp, 0, count);
                }
                outputStream.close();
            } catch (Exception ignored){
        }
            byte[] imageByte = outputStream.toByteArray();
            bytes.add(imageByte);
        });
        return bytes;
    }
}
