package jp.co.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.example.dao.HotelDao;
import jp.co.example.entity.HotelEntity;
import jp.co.example.form.HotelSearchForm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelDao hotelDao;

    public List<HotelEntity> findAll() {

        return hotelDao.findAll();
    }

    public List<HotelEntity> search(
            HotelSearchForm form) {

        if (form.getStayDate() == null
                || form.getStayDate().isBlank()
                || form.getCity() == null
                || form.getCity().isBlank()) {

            return hotelDao.findAll();
        }

        LocalDate stayDate =
                LocalDate.parse(form.getStayDate());

        if (form.getMaxPrice() != null) {

            return hotelDao.searchWithPrice(
                    stayDate,
                    form.getCity(),
                    form.getMaxPrice());
        }

        return hotelDao.searchWithoutPrice(
                stayDate,
                form.getCity());
    }

    public HotelEntity findById(
            String hotelId) {

        return hotelDao.selectById(hotelId);
    }
}