import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CartItem extends BaseEntity {
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    private int quantity;
 
}
