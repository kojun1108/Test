public class OrderDto{

    private int order;
    private String itemCode;
    private String itemName;
    private int price;
    private int quantity;
    private Date orderDate;
    private int orderTotal;
    private String memberCode;
    private String payment;

    private List<OrderDetailDto> details;
}
