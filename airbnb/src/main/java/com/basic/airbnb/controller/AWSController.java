package com.basic.airbnb.controller;

import com.basic.airbnb.service.impl.AwsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/aws")
public class AWSController
{

    @Autowired
    private AwsServiceImpl awsService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadPost(@RequestParam("file")MultipartFile file,@RequestParam String bucketName) throws IOException {
        String s = awsService.uploadFile(file.getOriginalFilename(),file , bucketName);
        return new ResponseEntity<>(s, HttpStatus.OK);

    }



}
