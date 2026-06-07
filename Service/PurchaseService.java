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
        cart.addItem(item);
    }

    public void remove(CartItemDto cart, String itemCode){
       cart.remove(itemCode);
    }

       public void update(CartItemDto cart, String itemCode, int quantity){
           cart.update(itemCode, quantity);
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
