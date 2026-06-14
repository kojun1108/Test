package jp.co.example.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        List<HotelEntity> result =
                hotelService.search(form);

        int pageSize = 5;

        int fromIndex = page * pageSize;

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
                        : result.subList(fromIndex, toIndex);

        int totalPages =
                (int)Math.ceil(
                        (double)result.size()
                                / pageSize);

        model.addAttribute("searchForm", form);
        model.addAttribute("hotelList", pageList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "hotel";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "hotel",
                hotelService.findById(id));

        return "hotel-detail";
    }
}