@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String loginForm() {

        return "login";
    }

    @PostMapping
    public String login(
            LoginForm form,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        UserSessionDto user =
                loginService.login(
                        form.getLoginId(),
                        form.getPassword());

        if (user == null) {

            redirectAttributes.addFlashAttribute(
                    "message",
                    "ログイン失敗");

            return "redirect:/login";
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

        return "redirect:/menu";
    }
}