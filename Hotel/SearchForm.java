package jp.co.example.form;

import lombok.Data;

@Data
public class HotelSearchForm {

    private String stayDate;

    private String city;

    private Integer maxPrice;
}