package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public String hotelList(
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Pageable pageable =
                PageRequest.of(page, 5);

        Page<Hotel> hotelPage =
                hotelRepository.findAll(pageable);

        model.addAttribute("hotelPage", hotelPage);
        model.addAttribute("currentPage", page);

        return "hotel";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable String id,
            Model model) {

        Hotel hotel =
                hotelRepository.findById(id)
                        .orElseThrow();

        model.addAttribute("hotel", hotel);

        return "hotel-detail";
    }
}