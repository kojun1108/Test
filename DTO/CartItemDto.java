public class CartItemDto {
    private List<ItemDto> items = new ArrayList<>();

    public Integer getTotalPrice() {

        Integer total = 0;

        for (CartItemDto item : items) {

            total += item.getPrice()

                    * item.getQuantity();

        }

        return total;

    }
    
    public void addItem(ItemDto item){
         //同じ商品があれば数量加算をする
         for(int i =0; i<items.size(); i++){
            ItemDto citem = items.get(i);
            if(citem.getItemCode().equals(item.getItemCode())){
                citem.setQuantity(citem.getQuantity() + item.getQuantity());
                return;
            }
         }    
        
        items.add(item);
    }
  
    public void update(String itemCode, int quantity){
        for(int i =0; i<items.size(); i++){
            ItemDto item = items.get(i);
            if(item.getItemCode().equals(itemCode)){
                item.setQuantity(quantity);
                break;
            }
        }
    }

    public void remove(String itemCode){

        for(int i =0; i<items.size(); i++){
            ItemDto item = items.get(i);
            if(item.getItemCode().equals(itemCode)){
                items.remove(i);
                break;
            }
        }
    }

    
}
