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
    public String purchaseComplete(MemberDto memberDto ,HttpSession session){
        UserDto cart = (UserDto) session.getAttribute("user");
        CartDto cart = (CartDto) session.getAttribute("cart");

        purchaseService.purchase(memberDto, cart);

        session.setAttribute("cart", new CartItemDto());

        //オーダーの中身を出す?
        
        return "complete";

        
    }
    
}
