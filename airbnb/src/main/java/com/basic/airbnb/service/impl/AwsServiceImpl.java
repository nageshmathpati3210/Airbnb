package com.basic.airbnb.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;


@Service
public class AwsServiceImpl
{
    private final AmazonS3 amazonS3;

    public AwsServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    public String uploadFile(String key, MultipartFile file,String bucketName) throws IOException {
        File file1 = convertMultiPartFileToFile(file);
        PutObjectResult putObjectResult = amazonS3.putObject(bucketName, key, file1);
        file1.delete();
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, key)
                        .withMethod(HttpMethod.GET);

        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();

    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException, IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    public void deleteFile(String key,String bucketName) {
        amazonS3.deleteObject(bucketName, key);
    }


    public S3Object downloadFile(String key, String bucketName) {
        return amazonS3.getObject(bucketName, key);
    }
}

