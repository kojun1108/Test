@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService {

    private final OrderMasterDao orderMasterDao;

    private final OrderDetailDao orderDetailDao;

    private final HotelDao hotelDao;

    private final FlightDao flightDao;

    /*カート追加*/
    public void addItem(CartItemDto cart, ItemDto item){
        //同じ商品があれば数量加算をする
         for(int i =0; i<cart.getItems().size(); i++){
            ItemDto citem = cart.getItems().get(i);
            if(citem.getItemCode().equals(item.getItemCode())){
                citem.setQuantity(citem.getQuantity() + item.getQuantity());
                return;
            }
         }      
        cart.addItem(item);
    }

    public void remove(CartItemDto cart, String itemCode){
        for(int i =0; i<cart.getItems().size(); i++){
            ItemDto item = cart.getItems().get(i);
            if(item.getItemCode().equals(itemCode)){
                cart.getItems().remove(i);
                break;
            }
        }
    }

       public void update(CartItemDto cart, String itemCode, int quantity){
        for(int i =0; i<cart.getItems().size(); i++){
            ItemDto item = cart.getItems().get(i);
            if(item.getItemCode().equals(itemCode)){
                item.setQuantity(quantity);
                break;
            }
        }
    }
    

    public void purchase(
            UserSessionDto user,
            CartDto cart) {

        OrderMasterEntity master =
                new OrderMasterEntity();

        master.setUserId(user.getUserId());

        master.setOrderDate(LocalDateTime.now());

        master.setTotalPrice(
                cart.getTotalPrice());

        orderMasterDao.insert(master);

        for (CartItemDto item : cart.getItems()) {

            OrderDetailEntity detail =
                    new OrderDetailEntity();

            detail.setOrderMasterId(
                    master.getOrderMasterId());

            detail.setItemType(
                    item.getItemType());

            detail.setItemId(
                    item.getItemId());

            detail.setItemName(
                    item.getItemName());

            detail.setPrice(
                    item.getPrice());

            detail.setQuantity(
                    item.getQuantity());

            orderDetailDao.insert(detail);

            if ("HOTEL".equals(item.getItemType())) {

                HotelEntity hotel =
                    hotelDao.selectById(
                        item.getItemId());

                hotel.setStock(
                    hotel.getStock() - 1);

                hotelDao.update(hotel);

            } else {

                FlightEntity flight =
                    flightDao.selectById(
                        item.getItemId());

                flight.setSeatCount(
                    flight.getSeatCount() - 1);

                flightDao.update(flight);
            }
        }
    }
}
