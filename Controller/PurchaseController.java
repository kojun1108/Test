@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart/add")
    public String addItem(
            CartItemDto item,
            HttpSession session) {

        CartDto cart =
                (CartDto) session
                        .getAttribute("cart");

        cartService.addItem(
                cart,
                item);

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(
            HttpSession session,
            Model model) {

        model.addAttribute(
                "cart",
                session.getAttribute(
                        "cart"));

        return "cart";
    }
}