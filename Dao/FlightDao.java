@Dao
@ConfigAutowireable
public interface FlightDao {

    @Select
    List<FlightEntity> selectAll();

    @Select
    FlightEntity selectById(Integer flightId);

    @Update(sqlFile = true)
    int updateSeat(FlightEntity entity);
}