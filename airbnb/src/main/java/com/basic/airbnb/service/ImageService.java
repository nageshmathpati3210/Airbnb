package com.basic.airbnb.service;

import com.basic.airbnb.entity.Image;
import com.basic.airbnb.entity.PropertyUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public Image uploadeImage(MultipartFile file, String bucketName,long propertyId, PropertyUser propertyUser) throws IOException;

}