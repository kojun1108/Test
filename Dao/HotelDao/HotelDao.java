@Dao
@ConfigAutowireable
public interface HotelDao {

    @Select
    List<Hotel> selectAll();

    @Select
    Hotel selectByItemcode(String itemCode);

    @Update(sqlFile = true)
    int update(Hotel hotel);
}
