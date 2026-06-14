package jp.co.example.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jp.co.example.entity.CartItem;
import jp.co.example.entity.HotelEntity;
import jp.co.example.form.CartForm;
import jp.co.example.service.CartService;
import jp.co.example.service.HotelService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final HotelService hotelService;

    private final CartService cartService;

    private final List<CartItem> cartList;

    @PostMapping("/add")
    public String addCart(
            @Valid @ModelAttribute CartForm form,
            BindingResult bindingResult,
            Model model) {

        HotelEntity hotel =
                hotelService.findById(
                        form.getHotelId());

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "hotel",
                    hotel);

            return "hotel-detail";
        }

        if (form.getQuantity()
                > hotel.getStock()) {

            model.addAttribute(
                    "hotel",
                    hotel);

            model.addAttribute(
                    "errorMessage",
                    "在庫数を超えています");

            return "hotel-detail";
        }

        CartItem item =
                new CartItem();

        item.setHotelId(
                hotel.getHotelId());

        item.setHotelName(
                hotel.getHotelName());

        item.setPrice(
                hotel.getPrice());

        item.setQuantity(
                form.getQuantity());

        cartList.add(item);

        return "redirect:/cart";
    }

    @GetMapping
    public String cart(
            Model model) {

        model.addAttribute(
                "cartList",
                cartList);

        model.addAttribute(
                "totalPrice",
                cartService.calcTotalPrice(
                        cartList));

        return "cart";
    }

    @PostMapping("/delete")
    public String delete(
            int index) {

        cartList.remove(index);

        return "redirect:/cart";
    }
}