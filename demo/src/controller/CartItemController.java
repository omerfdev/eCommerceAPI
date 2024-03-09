import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/{cartId}/add")
    public CartItem addProductToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        // implement add product to cart logic
        return cartItemService.saveCartItem(cartItem);
    }

    @DeleteMapping("/{cartItemId}")
    public void removeProductFromCart(@PathVariable Long cartItemId) {
        // implement remove product from cart logic
        cartItemService.deleteCartItem(cartItemId);
    }
}
