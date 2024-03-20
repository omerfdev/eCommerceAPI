import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartItemMapper cartItemMapper;

    public List<CartItemDTO> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems.stream()
                .map(cartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CartItemDTO getCartItemById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        if (cartItem == null) {
            throw new NotFoundException("CartItem not found with id: " + id);
        }
        return cartItemMapper.toDTO(cartItem);
    }

    public CartItemDTO saveCartItem(CartItemDTO cartItemDTO) {
        if (!isProductAvailable(cartItemDTO.getProductId())) {
            throw new OutOfStockException("Product with id " + cartItemDTO.getProductId() + " is out of stock.");
        }
        if (!isValidCartAndProduct(cartItemDTO.getCartId(), cartItemDTO.getProductId())) {
            throw new BadRequestException("Invalid cartId or productId.");
        }
        
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDTO(cartItem);
    }

    public void deleteCartItem(Long id) {
        if (getCartItemById(id) == null) {
            throw new NotFoundException("CartItem not found with id: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    // Ürünün stok durumunu kontrol eden yardımcı metod
    private boolean isProductAvailable(Long productId) {
        // Gerçek uygulamada, productId'ye göre ürün stok durumu kontrol edilir.
        // Eğer ürün stokta varsa true, yoksa false döner.
        // Bu sadece bir örnek olarak verilmiştir.
        return getProductStock(productId) > 0;
    }

    // CartId ve ProductId alanlarının doğruluğunu kontrol eden yardımcı metod
    private boolean isValidCartAndProduct(Long cartId, Long productId) {
        // Gerçek uygulamada, cartId ve productId'nin geçerliliği kontrol edilir.
        // Eğer her ikisi de geçerliyse true, değilse false döner.
        // Bu sadece bir örnek olarak verilmiştir.
        return cartId != null && productId != null;
    }

    // Gerçek uygulamada, productId'ye göre ürün stok durumu kontrol edilir.
    // Bu sadece bir örnek olarak verilmiştir.
    private int getProductStock(Long productId) {
        // Ürün stoğunu veritabanından veya başka bir servisten kontrol ederken gerçek stok miktarını alın.
        // Bu sadece bir örnek olarak verilmiştir.
        return 10; // Varsayılan olarak 10 adet ürün stoğu olduğunu varsayalım.
    }
}
