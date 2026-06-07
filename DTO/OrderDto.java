public class OrderDto{

    private List<CartItemDto> items = new ArrayList<>();

    public Integer getTotalPrice() {

        return items.stream()
                .mapToInt(x -> x.getPrice() * x.getQuantity())
                .sum();
    }
}
