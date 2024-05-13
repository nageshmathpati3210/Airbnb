package com.basic.airbnb.service;

import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.payload.LoinDto;
import com.basic.airbnb.payload.PropertyUserDto;

public interface PropertyUserService
{
    public PropertyUser addUser(PropertyUserDto propertyUserDto);

    String verifyLogin(LoinDto loginDto);
}
