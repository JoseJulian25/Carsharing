package com.rd.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageVehicleService {
    String uploadImage(MultipartFile file, Integer vehicleId) throws IOException;
    List<byte[]> downloadImage(Integer vehicleId);
}
