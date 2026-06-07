@Dao
@ConfigAutowireable
public interface MemberDao {

    @Select
    List<FlightEntity> selectAll();

    @Select
    FlightEntity selectById(Integer flightId);

    @Update(sqlFile = true)
    int updateSeat(FlightEntity entity);
}
