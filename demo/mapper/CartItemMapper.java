import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO toDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setCartId(cartItem.getCart().getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public CartItem toEntity(CartItemDTO dto) {
        CartItem cartItem = new CartItem();
        cartItem.setId(dto.getId());
        // Ayrıca, DTO'dan gelen cartId ve productId bilgileri kullanılarak bu nesnelerin alınması sağlanabilir.
        // Burada sadece örnek olarak set edilmiştir.
        Cart cart = new Cart();
        cart.setId(dto.getCartId());
        cartItem.setCart(cart);
        Product product = new Product();
        product.setId(dto.getProductId());
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        return cartItem;
    }
}
