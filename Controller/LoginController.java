@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @PostMapping("/login")
    public String login(
            LoginForm form,
            HttpSession session,
            Model model) {

        UserSessionDto user =
                loginService.login(
                        form.getLoginId(),
                        form.getPassword());

        if (user == null) {

            model.addAttribute(
                    "message",
                    "ログイン失敗");

            return "login";
        }

        session.setAttribute(
                "user",
                user);

        return "redirect:/menu";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}