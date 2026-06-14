package jp.co.example.controller;

import java.util.Collections;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jp.co.example.entity.HotelEntity;
import jp.co.example.form.HotelSearchForm;
import jp.co.example.service.HotelService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public String hotel(
            HotelSearchForm form,
            @RequestParam(defaultValue = "0")
            int page,
            Model model) {

        List<HotelEntity> result =
                hotelService.search(form);

        createPage(
                result,
                page,
                model);

        model.addAttribute(
                "searchForm",
                form);

        return "hotel";
    }

    @GetMapping("/search")
    public String search(
            @Valid HotelSearchForm form,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "0")
            int page,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "hotelList",
                    Collections.emptyList());

            model.addAttribute(
                    "currentPage",
                    0);

            model.addAttribute(
                    "totalPages",
                    0);

            return "hotel";
        }

        List<HotelEntity> result =
                hotelService.search(form);

        createPage(
                result,
                page,
                model);

        model.addAttribute(
                "searchForm",
                form);

        return "hotel";
    }

 @GetMapping("/detail/{id}")
public String detail(
        @PathVariable String id,
        HotelSearchForm searchForm,
        Model model) {

    HotelEntity hotel =
            hotelService.findById(id);

    CartForm cartForm =
            new CartForm();

    cartForm.setHotelId(id);
    cartForm.setQuantity(1);

    model.addAttribute("hotel", hotel);
    model.addAttribute("searchForm", searchForm);
    model.addAttribute("cartForm", cartForm);

    return "hotel-detail";
}

    private void createPage(
            List<HotelEntity> result,
            int page,
            Model model) {

        int pageSize = 5;

        int fromIndex =
                page * pageSize;

        if (fromIndex >= result.size()) {

            fromIndex = 0;
            page = 0;
        }

        int toIndex =
                Math.min(
                        fromIndex + pageSize,
                        result.size());

        List<HotelEntity> pageList =
                result.isEmpty()
                        ? Collections.emptyList()
                        : result.subList(
                                fromIndex,
                                toIndex);

        int totalPages =
                (int) Math.ceil(
                        (double) result.size()
                                / pageSize);

        model.addAttribute(
                "hotelList",
                pageList);

        model.addAttribute(
                "currentPage",
                page);

        model.addAttribute(
                "totalPages",
                totalPages);
    }
}