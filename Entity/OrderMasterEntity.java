@Entity
@Table(name = "order_master")
public class OrderMasterEntity {

    @Id
    Integer orderNo;
    Integer orderMasterId;
    String paymentMethod;

    Integer userId;

    LocalDateTime orderDate;

    Integer totalPrice;
}
