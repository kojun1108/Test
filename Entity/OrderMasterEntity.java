@Entity
@Table(name = "order_master")
public class OrderMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderMasterId;

    Integer userId;

    LocalDateTime orderDate;

    Integer totalPrice;
}
