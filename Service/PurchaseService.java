@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderMasterDao orderMasterDao;

    private final OrderDetailDao orderDetailDao;

    private final HotelDao hotelDao;

    private final FlightDao flightDao;

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

                hotelDao.updateStock(hotel);

            } else {

                FlightEntity flight =
                    flightDao.selectById(
                        item.getItemId());

                flight.setSeatCount(
                    flight.getSeatCount() - 1);

                flightDao.updateSeat(flight);
            }
        }
    }
}