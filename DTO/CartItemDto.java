public class CartItemDto {
    private List<ItemDto> items = new ArrayList<>();
    private int totalPrice;

    public void totalPrice(){
        int x = 0;
        for(ItemDto i : items.getItems()){
            x += i.getSubtotal();
        }
        totalPrice = x;
    };
    public void addItem(ItemDto item){
        items.add(item);
    };

    
    public void update(String itemCode, int quantity{
    };

    public void remove(String itemCode){
};

    
}
