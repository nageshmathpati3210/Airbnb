package com.basic.airbnb.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto
{
    private long MobileNo;

    private long bookingId;

    private String guestName;

    private int price;

    private int totalPrice;

}
