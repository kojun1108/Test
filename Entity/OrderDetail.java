@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderDetailId;

    Integer orderMasterId;

    String itemType;

    Integer itemId;

    String itemName;

    Integer price;

    Integer quantity;
}