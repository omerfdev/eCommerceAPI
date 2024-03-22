import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CartItem extends BaseEntity {
    @ManyToOne
    private Cart cart;
    
    @ManyToOne
    private Product product;
    
    private int quantity;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
