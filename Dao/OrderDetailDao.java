@Dao
@ConfigAutowireable
public interface OrderDetailDao {

    @Insert
    int insert(OrderDetailEntity entity);
}