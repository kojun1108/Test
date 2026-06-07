package com.example.travel.dto;

public class FlightDto
        extends ItemDto {

private String flightCode;
private Time departureTime;
                private Time arrivalTime;
                private String departureAiportCode;
                private String basicPrice;
                private String aiportCode;
                private Date date;
                private int price;
                private int stock;
                private int version;
                private String name;

    @Override
    public String getItemType() {
        return "FLIGHT";
    }
}
