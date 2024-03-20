import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setCustomerId(cart.getCustomer().getId());
        cartDTO.setCartItems(cart.getCartItems().stream().map(cartItemMapper::toDTO).collect(Collectors.toList()));
        return cartDTO;
    }

    public Cart toEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        // Eğer Customer entity'si ile ilişkili ise, customer'ı set etmek için gerekli
        // işlemler yapılmalıdır.
        // Bu örnekte direkt customerId kullanılmıştır.
        // cart.setCustomer(customerService.getCustomerById(cartDTO.getCustomerId()));
        // // Örnek olarak
        return cart;
    }
}
