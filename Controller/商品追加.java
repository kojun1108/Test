@PostMapping("/cart/add")
public String addItem(

        @RequestParam Integer itemId,

        @RequestParam Integer quantity,

        @ModelAttribute("cart")
        CartDto cart) {

    cartOrderService.addItem(
            cart,
            itemId,
            quantity);

    return "redirect:/cart";
}




商品画面

<form th:action="@{/cart/add}"
      method="post">

    <input
        type="hidden"
        name="itemId"
        th:value="${item.itemId}" />

    <input
        type="hidden"
        name="quantity"
        value="1" />

    <button type="submit">

        カート追加

    </button>

</form>


@Service
public class CartOrderService {

    private final HotelDao hotelDao;

    private final FlightDao flightDao;

    public void addItem(
            CartDto cart,
            Integer itemId,
            Integer quantity) {

        HotelEntity hotel =
                hotelDao.selectById(
                        itemId);

        if (hotel != null) {

            HotelCartItemDto item =
                    new HotelCartItemDto();

            item.setItemId(
                    hotel.getHotelId());

            item.setItemName(
                    hotel.getHotelName());

            item.setPrice(
                    hotel.getPrice());

            item.setQuantity(
                    quantity);

            cart.addItem(item);

            return;
        }

        FlightEntity flight =
                flightDao.selectById(
                        itemId);

        if (flight != null) {

            FlightCartItemDto item =
                    new FlightCartItemDto();

            item.setItemId(
                    flight.getFlightId());

            item.setItemName(
                    flight.getFlightName());

            item.setPrice(
                    flight.getPrice());

            item.setQuantity(
                    quantity);

            cart.addItem(item);

            return;
        }

        throw new RuntimeException(
                "商品が存在しません");
    }
}


CartDto
public void addItem(
        CartItemDto newItem) {

    for (CartItemDto item
            : items) {

        if (item.getItemId()
                .equals(
                        newItem.getItemId())) {

            item.setQuantity(
                    item.getQuantity()
                    + newItem.getQuantity());

            return;
        }
    }

    items.add(newItem);
}



public void addItem(
        CartDto cart,
        Integer itemId,
        Integer quantity) {

    HotelEntity hotel =
            hotelDao.selectById(itemId);

    if (hotel != null) {

        if (quantity > hotel.getStock()) {

            throw new IllegalArgumentException(
                    "在庫数を超えています");
        }

        HotelCartItemDto item =
                new HotelCartItemDto();

        item.setItemId(
                hotel.getHotelId());

        item.setItemName(
                hotel.getHotelName());

        item.setPrice(
                hotel.getPrice());

        item.setQuantity(
                quantity);

        cart.addItem(item);

        return;
    }

    FlightEntity flight =
            flightDao.selectById(itemId);

    if (flight != null) {

        if (quantity > flight.getSeatCount()) {

            throw new IllegalArgumentException(
                    "残席数を超えています");
        }

        FlightCartItemDto item =
                new FlightCartItemDto();

        item.setItemId(
                flight.getFlightId());

        item.setItemName(
                flight.getFlightName());

        item.setPrice(
                flight.getPrice());

        item.setQuantity(
                quantity);

        cart.addItem(item);

        return;
    }

    throw new IllegalArgumentException(
            "商品が存在しません");
}




@PostMapping("/cart/add")
public String addItem(

        @RequestParam Integer itemId,

        @RequestParam Integer quantity,

        @ModelAttribute("cart")
        CartDto cart,

        Model model) {

    try {

        cartOrderService.addItem(
                cart,
                itemId,
                quantity);

        return "redirect:/cart";

    } catch (IllegalArgumentException e) {

         model.addAttribute(

                "errorMessage",

                e.getMessage());

        HotelEntity hotel =

                hotelDao.selectById(

                        itemId);

        if (hotel != null) {

            model.addAttribute(

                    "hotel",

                    hotel);

            return "hotelDetail";

        }

        FlightEntity flight =

                flightDao.selectById(

                        itemId);

        model.addAttribute(

                "flight",

                flight);

        return "flightDetail";

    }
}


<p th:if="${errorMessage}"
   th:text="${errorMessage}">
</p>

