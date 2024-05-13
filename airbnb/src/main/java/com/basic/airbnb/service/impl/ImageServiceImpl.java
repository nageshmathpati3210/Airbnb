package com.basic.airbnb.service.impl;

import com.basic.airbnb.entity.Image;
import com.basic.airbnb.entity.Property;
import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.repository.ImageRepository;
import com.basic.airbnb.repository.PropertyRepository;
import com.basic.airbnb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService
{


    private PropertyRepository propertyRepository;
    private AwsServiceImpl awsService;

    @Autowired
    private ImageRepository imageRepository;


    public ImageServiceImpl(PropertyRepository propertyRepository, AwsServiceImpl awsService) {
        this.propertyRepository = propertyRepository;
        this.awsService = awsService;
    }


    @Override
    public Image uploadeImage(MultipartFile file, String bucketName, long propertyId, PropertyUser propertyUser) throws IOException
    {
        String s = awsService.uploadFile(file.getOriginalFilename(), file, bucketName);
        Property property = propertyRepository.findById(propertyId).get();
        Image image=new Image();
        image.setUrl(s);
        image.setProperty(property);
        image.setPropertyUser(propertyUser);
        Image save = imageRepository.save(image);

        return save;

    }
}
