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
    

    public void purchaseComplete(MemberDto memberDto , String paymentMethod, CartDto cart) {
        //注文番号はテーブルを参照して、一番小さい番号に1を足す
        Integer orderNo = orderMasterDao.selectMaxorderNo();
        if(orderNo == NULL){
            orderNo = 1;
        }else{
            orderNo += 1;
        
        OrderMaster master = new OrderMaster();
         master.setOrderNo(orderNo);

        master.setMemberCode(memberDto.getMemberCode());

        master.setPaymentMethod(paymentMethod);

        master.setOrderDate(LocalDateTime.now());

        master.setTotalPrice(cart.getTotalPrice());

        //マスターテーブルに追加
        orderMasterDao.insert(master);

        List<OrderDetailDto> detailList = new ArrayList<>();

        for (CartItemDto item : cart.getItems()) {

            OrderDetail detail =　new OrderDetail();

            detail.setOrderMasterId(master.getOrderMasterId());

            detail.setItemType(item.getItemType());

            detail.setItemCode(item.getItemCode());

            detail.setItemName(item.getItemName());

            detail.setPrice(item.getPrice());

            detail.setQuantity(item.getQuantity());

            
            //detailテーブルに追加
            orderDetailDao.insert(detail);

            //画面商品表示用のorderdetaildtoを作成
            OrderdetaiDto dto = new OrderDetailDto();

            dto.setItemCode(detail.getItemCode());

            detailList.add(dto);
        }

        //画面表示用の注文情報
        OrderDto order = new OrderDto();

        order.setOrderNo(orderNo);

        order.setDetails(detailList);

        //在庫更新

            if ("HOTEL".equals(item.getItemType())) {

                HotelEntity hotel = hotelDao.selectById(item.getItemId());
                
                //ここでマイナスならエラー投げる
                hotel.setStock(hotel.getStock() - item.getQuantity());

                hotelDao.update(hotel);

            } else {

                FlightEntity flight =flightDao.selectById(item.getItemId());

                flight.setStock(flight.getSeatCount() - item.getQuantity());

                flightDao.update(flight);
            }
        }
    }
}
