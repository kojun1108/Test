package jp.co.example.dao;

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
            String stayDate,
            String city,
            Integer maxPrice);

    @Select
    List<HotelEntity> searchWithoutPrice(
            String stayDate,
            String city);

    @Select
    HotelEntity selectById(String hotelId);
}