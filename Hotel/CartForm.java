package jp.co.example.form;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartForm {

    private String hotelId;

    @Min(value = 1, message = "数量は1以上で入力してください")
    private Integer quantity;
}