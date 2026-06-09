@PostMapping("/cart/update")
public String updateCart(
        @ModelAttribute("cart")
        CartDto cart) {

    cartOrderService.updateCart(
            cart);

    return "redirect:/cart";
}

public void updateCart(
        CartDto cart) {

    for (CartItemDto item
            : cart.getItems()) {

        if (item.getQuantity() < 1) {

            item.setQuantity(1);
        }
    }
}