package com.example.travel.dto;

public class HotelDto
        extends ItemDto {

                private String hotelCode;
                private String cityCode;
                private String basicPrice;
                private String grade;
                private int stock;
                private Date date;
                private int price;
                private int version;
                private String name;
    @Override
    public String getItemType() {
        return "HOTEL";
    }
}
