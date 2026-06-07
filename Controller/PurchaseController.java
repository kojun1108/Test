@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/showCart")
    public String showCart(HttpSession session, Model model) {
        CartItemDto cart = (CartItemDto) sesssion.getAttribute("cart");

        model.addAttribute("cart", cart);

        return "cart";
    }
    
    @PostMapping("/addItem")
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

  

    @PostMapping("/remove")

    @PostMapping("/update")

    @GetMapping("/confirm")

    @GetMapping("/backCart")
    public String cart(
            HttpSession session,
            Model model) {

        model.addAttribute(
                "cart",
                session.getAttribute(
                        "cart"));

        return "cart";
    }

    @PostMapping("/searchMember")

    @PostMapping("/purchaseComplete")
    
}
