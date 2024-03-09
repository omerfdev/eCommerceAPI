import javax.persistence.Entity;

@Entity
public class Product extends BaseEntity {
    private String name;
    private double price;
    private int stockQuantity;
   
}
