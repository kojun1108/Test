@Controller
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseServise purchaseService;

    @GetMapping("/showCart")
    public String showCart(HttpSession session, Model model) {
        CartItemDto cart = (CartItemDto) sesssion.getAttribute("cart");

        model.addAttribute("cart", cart);

        return "cart";
    }
    
    @PostMapping("/addItem")
    public String addItem(@ModelAttribute CartItemDto item, HttpSession session) {

        CartDto cart = (CartDto) session.getAttribute("cart");

        purchaseService.addItem(cart, item);

        return "cart";
    }

  

    @PostMapping("/remove")
     public String remove(String itemCode, HttpSession session) {

        CartDto cart = (CartDto) session.getAttribute("cart");

        purchaseService.remove(cart, itemCode);

        return "cart";
    }

    @PostMapping("/update")
    public String update(String itemCode, int quantity, HttpSession session){
       CartDto cart = (CartDto) session.getAttribute("cart");
    
        purchaseService.update(cart, itemCode, quantity);

        return cart;
    }
@GetMapping("/confirm")
public String init(
        HttpSession session,
        Model model) {

    LoginUser loginUser =
            (LoginUser) session.getAttribute("loginUser");

    MemberDto memberDto = null;

    if ("CUSTOMER".equals(loginUser.getRole())) {

        memberDto = memberService.getMember(
                loginUser.getMemberCode());
    }

    model.addAttribute("memberDto", memberDto);

    return "purchaseConfirm";
}
    

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
    public String purchaseComplete(MemberDto memberDto , String paymentMethod, HttpSession session, Model model){
        CartDto cart = (CartDto) session.getAttribute("cart");

        OrderDto order = purchaseService.purchase(memberDto , paymentMethod, cart);

        model.addAttribute("order", order);

        session.setAttribute("cart", new CartItemDto());

cart.clear();
        
        return "complete";

        
    }
    
}
