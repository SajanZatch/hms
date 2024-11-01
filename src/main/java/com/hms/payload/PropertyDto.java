package com.hms.payload;

import com.hms.entity.City;
import com.hms.entity.Country;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PropertyDto {


    private String name;


    private Integer no_of_guests;


    private Integer no_of_bedrooms;


    private Integer no_of_bathrooms;


    private Integer no_of_beds;


    private Country country;


    private City city;
}
