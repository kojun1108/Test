@Dao
@ConfigAutowireable
public interface UserDao {

    @Select
    UserEntity login(
        String loginId,
        String password
    );
}