package jp.co.example.service;

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

        boolean hasDate =
                form.getStayDate() != null
                && !form.getStayDate().isBlank();

        boolean hasCity =
                form.getCity() != null
                && !form.getCity().isBlank();

        if (!hasDate || !hasCity) {
            return hotelDao.findAll();
        }

        if (form.getMaxPrice() != null) {

            return hotelDao.searchWithPrice(
                    form.getStayDate(),
                    form.getCity(),
                    form.getMaxPrice());
        }

        return hotelDao.searchWithoutPrice(
                form.getStayDate(),
                form.getCity());
    }

    public HotelEntity findById(String hotelId) {

        return hotelDao.selectById(hotelId);
    }
}