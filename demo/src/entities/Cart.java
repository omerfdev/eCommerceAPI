import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends BaseEntity {
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();
   
}
