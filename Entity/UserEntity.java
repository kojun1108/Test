@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    Integer userId;

    String loginId;

    String password;

    String userName;
}
