package jp.co.example.dao;

import java.time.LocalDate;
import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.example.entity.HotelEntity;

@Dao
@ConfigAutowireable
public interface HotelDao {

    @Select
    List<HotelEntity> findAll();

    @Select
    List<HotelEntity> searchWithPrice(
            LocalDate stayDate,
            String city,
            Integer maxPrice);

    @Select
    List<HotelEntity> searchWithoutPrice(
            LocalDate stayDate,
            String city);

    @Select
    HotelEntity selectById(String hotelId);
}