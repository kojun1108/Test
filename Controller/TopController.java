@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu")
    public String menu(
            HttpSession session,
            Model model) {

        CartDto cart =
                (CartDto) session.getAttribute("cart");

        if (cart == null) {

            cart = new CartDto();

            session.setAttribute(
                    "cart",
                    cart);
        }

        model.addAttribute(
                "hotelList",
                menuService.getHotels());

        model.addAttribute(
                "flightList",
                menuService.getFlights());

        return "menu";
    }
}