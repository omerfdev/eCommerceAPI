import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public CartDTO getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    @Transactional
    public CartDTO createCart(@RequestBody CartDTO cartDTO) {
        // Sepete ekleme işlemi sırasında, sipariş miktarının stoktan fazla olup olmadığını kontrol et.
        for (CartItemDTO item : cartDTO.getCartItems()) {
            if (!cartService.isProductAvailable(item.getProductId(), item.getQuantity())) {
                throw new OutOfStockException("Product with id " + item.getProductId() + " is out of stock.");
            }
        }
        return cartService.saveCart(cartDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
