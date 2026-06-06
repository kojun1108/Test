@GetMapping("/menu")
public String menu(
        HttpSession session,
        Model model) {

    CartDto cart =
        (CartDto)session.getAttribute("cart");

    if (cart == null) {

        cart = new CartDto();

        session.setAttribute(
            "cart",
            cart);
    }

    model.addAttribute(
        "hotels",
        menuService.getHotels());

    model.addAttribute(
        "flights",
        menuService.getFlights());

    return "menu";
}