import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Transactional(readOnly = true)
    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            throw new NotFoundException("Cart not found with id: " + id);
        }
        return cartMapper.toDTO(cart);
    }

    @Transactional
    public CartDTO saveCart(CartDTO cartDTO) {
        Cart cart = cartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    @Transactional
    public void deleteCart(Long id) {
        CartDTO existingCart = getCartById(id);
        if (existingCart == null) {
            throw new NotFoundException("Cart not found with id: " + id);
        }
        cartRepository.deleteById(id);
    }

    // Ürünün stok durumunu kontrol eden yardımcı metod
    private boolean isProductAvailable(Long productId, int quantity) {       
        // Eğer ürün stokta yeterli miktarda bulunuyorsa true, yoksa false döner.
       
        int productStock = getProductStock(productId);
        return productStock >= quantity; // Ürün stoğu, istenen miktar kadar veya daha fazlaysa true döner.
    }

}
