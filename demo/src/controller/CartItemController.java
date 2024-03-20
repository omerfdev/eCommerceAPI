import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/{cartId}/add")
    @Transactional // rollback yapma imkanı sağlar.Bir servis çalışmadığında ondan önce çalışmış
                   // olan servislerin yapmış olduğu değişikleri geri alır.
    public CartItemDTO addProductToCart(@PathVariable Long cartId, @RequestBody CartItemDTO cartItemDTO) {
        return cartItemService.saveCartItem(cartItemDTO);
    }

    @DeleteMapping("/{cartItemId}")
    @Transactional
    public void removeProductFromCart(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    @GetMapping("/{cartItemId}")
    public CartItemDTO getCartItemById(@PathVariable Long cartItemId) {
        return cartItemService.getCartItemById(cartItemId);
    }
}
