import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderItemDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
        return OrderItemMapper.toDTO(orderItem);
    }

    @Transactional
    public OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO) {
        // Stok kontrolü yapılıyor
        if (!isStockAvailable(orderItemDTO.getProductId(), orderItemDTO.getQuantity())) {
            throw new InsufficientStockException("Insufficient stock for product with ID: " + orderItemDTO.getProductId());
        }

        // Stok yeterli ise sipariş kalemi kaydediliyor
        OrderItem orderItem = OrderItemMapper.toEntity(orderItemDTO);
        orderItem = orderItemRepository.save(orderItem);
        return OrderItemMapper.toDTO(orderItem);
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    private boolean isStockAvailable(Long productId, int quantity) {
        // Burada stok kontrolü gerçekleştirilir, gerekirse envanterden kontrol edilir
        // Örnek amaçlı her zaman stok varmış gibi davranıyoruz
        return true;
    }
}
