package com.basic.airbnb;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class AirbnbApplication
{

	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}



}
