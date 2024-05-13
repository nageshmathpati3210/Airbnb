package com.basic.airbnb.controller;


import com.basic.airbnb.entity.Image;
import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.service.ImageService;
import com.basic.airbnb.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/image")
public class ImageController
{
    @Autowired
    private ImageServiceImpl imageService;


    @PostMapping("/uploade")
    public ResponseEntity<?> uploadImage(String bucketName, @RequestParam("file") MultipartFile file,long propertyId, @AuthenticationPrincipal PropertyUser user) throws IOException {

        Image image = imageService.uploadeImage(file, bucketName, propertyId, user);
        return ResponseEntity.status(HttpStatus.OK).body(image);

    }

}
