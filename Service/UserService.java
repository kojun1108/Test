@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDao userDao;

    public UserSessionDto login(
            String loginId,
            String password) {

        UserEntity user =
            userDao.login(loginId, password);

        if (user == null) {
            return null;
        }

        UserSessionDto dto = new UserSessionDto();

        dto.setUserId(user.getUserId());
        dto.setLoginId(user.getLoginId());
        dto.setUserName(user.getUserName());

        return dto;
    }
}