package com.rd.utils;

import com.rd.entity.ImageVehicle;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            byte[] tmp = new byte[4 * 1024];
            while (!deflater.finished()) {
                int size = deflater.deflate(tmp);
                outputStream.write(tmp, 0, size);
            }
            return outputStream.toByteArray();
        }catch (Exception ignored) {
            return null;
        }finally {
            deflater.end();
        }
    }

    public static List<ImageVehicle> decompressImage(List<ImageVehicle> imageVehicles){
        imageVehicles.forEach(image -> {
            Inflater inflater = new Inflater();
            inflater.setInput(image.getImageData());

            try( ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.getImageData().length)){
                byte[] tmp = new byte[4*1024];
                while(!inflater.finished()){
                    int count = inflater.inflate(tmp);
                    outputStream.write(tmp, 0, count);
                }
                image.setImageData(outputStream.toByteArray());
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }finally {
                inflater.end();
            }
        });
        return imageVehicles;
    }
}
