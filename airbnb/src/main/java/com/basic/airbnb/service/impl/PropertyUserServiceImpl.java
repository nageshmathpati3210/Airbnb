package com.basic.airbnb.service.impl;

import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.payload.LoinDto;
import com.basic.airbnb.payload.PropertyUserDto;
import com.basic.airbnb.repository.PropertyUserRepository;
import com.basic.airbnb.service.PropertyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyUserServiceImpl  implements PropertyUserService
{

    @Autowired
    private PropertyUserRepository propertyUserRepository;

    @Autowired
     private JwtService jwtService;
    @Override
    public PropertyUser addUser(PropertyUserDto propertyUserDto)
    {

        PropertyUser propertyUser=new PropertyUser();
        propertyUser.setFirstName(propertyUserDto.getFirstName());
        propertyUser.setLastName(propertyUserDto.getLastName());
        propertyUser.setEmail(propertyUserDto.getEmail());
        propertyUser.setUserRole(propertyUserDto.getUserRole());
        propertyUser.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        propertyUser.setUsername(propertyUserDto.getUsername());

        PropertyUser save = propertyUserRepository.save(propertyUser);

        return save;
    }

    @Override
    public String verifyLogin(LoinDto loginDto)
    {
        Optional<PropertyUser> byUsername = propertyUserRepository.findByUsername(loginDto.getUsername());

        if(byUsername.isPresent())
        {
            PropertyUser propertyUser = byUsername.get();

            if(BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword()))
            {
               String s = jwtService.generateJwtToken(propertyUser);
                return s;
            }
        }
        return null;
    }


}
