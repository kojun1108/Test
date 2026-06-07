@Dao
@ConfigAutowireable
public interface MemberDao {

    @Select
    Member selectById(String memberCode);

    @Select
    Member selectByMail(String mail);

    @insert(sqlFile = true)
    int insert(Member member);
}
