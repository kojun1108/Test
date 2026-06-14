package jp.co.example.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelSearchForm {

    @NotBlank(message = "宿泊日を入力してください")
    private String stayDate;

    @NotBlank(message = "都市を選択してください")
    private String city;

    private Integer maxPrice;
}