package com.basic.airbnb.controller;


import com.basic.airbnb.entity.Booking;
import com.basic.airbnb.entity.Property;
import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.payload.BookingDto;
import com.basic.airbnb.repository.BookingRepository;
import com.basic.airbnb.repository.PropertyRepository;
import com.basic.airbnb.service.impl.AwsServiceImpl;
import com.basic.airbnb.service.impl.PDFService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController
{


    private BookingRepository bookingRepository;

    private PropertyRepository propertyRepository;

    @Autowired
    private PDFService pdfService;

    public BookingController(BookingRepository bookingRepository, PropertyRepository propertyRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/createBooking/{propertyId}")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking, @AuthenticationPrincipal PropertyUser user, @PathVariable long propertyId) throws DocumentException, IOException {

        Property property = propertyRepository.findById(propertyId).get();
        booking.setProperty(property);
        booking.setPropertyUser(user);
        int totalPrice= property.getNightlyPrice()*booking.getTotalNights();
        booking.setTotalPrice(totalPrice);
        Booking save = bookingRepository.save(booking);

        BookingDto dto=new BookingDto();
        dto.setBookingId(save.getId());
        dto.setPrice(save.getTotalPrice());
        dto.setGuestName(save.getGuestName());
        dto.setTotalPrice(totalPrice);
        dto.setMobileNo(booking.getMobileNo());

        boolean b = pdfService.genratePdf("D:\\AirbnbPdf\\booking-confirmation-id:" + save.getId()+".pdf", dto);

        if(b)
        {
            return new ResponseEntity<>("booking sucessfull",HttpStatus.CREATED);
        }
        else {

            return new ResponseEntity<>("Something Went Wrong ", HttpStatus.BAD_REQUEST);
        }

    }

}
