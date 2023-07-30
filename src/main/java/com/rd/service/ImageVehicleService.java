package com.rd.service;

import com.rd.entity.ImageVehicle;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageVehicleService {
    List<String> uploadImage(List<MultipartFile> file, Integer vehicleId) throws IOException;
    List<ImageVehicle> downloadImage(Integer vehicleId);
}
