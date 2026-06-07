@Dao
@ConfigAutowireable
public interface OrderMasterDao {

    @Insert
    int insert(OrderMasterEntity entity);

    @Select
    Integer selectMaxOrderNo();
}
