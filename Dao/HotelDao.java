@Dao
@ConfigAutowireable
public interface HotelDao {

    @Select
    List<HotelEntity> selectAll();

    @Select
    HotelEntity selectById(Integer hotelId);

    @Update(sqlFile = true)
    int updateStock(HotelEntity entity);
}