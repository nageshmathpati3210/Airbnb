package com.basic.airbnb.service.impl;

import com.basic.airbnb.payload.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class PDFService {


    @Autowired
    private AwsServiceImpl awsService;

    @Autowired
    private SMSservice smSservice;

    public boolean genratePdf(String filepath, BookingDto dto) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filepath));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(dto.getGuestName(), font);
        Chunk chunk1 = new Chunk(String.valueOf(dto.getBookingId()), font);
        Chunk chunk2 = new Chunk(String.valueOf(dto.getPrice()), font);
        Chunk chunk3 = new Chunk(String.valueOf(dto.getTotalPrice()), font);


        document.add(chunk);
        document.add(new Paragraph("\n"));
        document.add(chunk1);
        document.add(new Paragraph("\n"));
        document.add(chunk2);
        document.add(new Paragraph("\n"));
        document.add(chunk3);
        document.add(new Paragraph("\n"));
        document.add(chunk);
        document.close();


        MultipartFile convert = PDFService.convert(new File(filepath));

        String myairbnb3210 = awsService.uploadFile(dto.getGuestName(), convert, "myairbnb3210");
        smSservice.sendSms(String.valueOf(dto.getMobileNo()),"test"+myairbnb3210);
        System.out.println(myairbnb3210);

        return true;

    }


    public static MultipartFile convert(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                "application/octet-stream", inputStream);
        return multipartFile;
    }

}
