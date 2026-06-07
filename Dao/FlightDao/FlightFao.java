@Dao
@ConfigAutowireable
public interface FlightDao {

    @Select
    List<Flight> selectAll();

    @Select
    Flight selectByItemcode(String itemCode);

    @Update(sqlFile = true)
    int update(Flight flight);
}
