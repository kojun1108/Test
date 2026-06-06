@PostMapping("/login")
public String login(
        LoginForm form,
        HttpSession session) {

    UserSessionDto user =
            loginService.login(
                    form.getLoginId(),
                    form.getPassword());

    session.setAttribute(
            "user",
            user);

    return "redirect:/menu";
}