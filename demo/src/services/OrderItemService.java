import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        // Sipariş kalemi kaydedilirken gerekli işlemler burada yapılabilir, örneğin fiyat güncellemesi.
        // Ayrıca, sipariş kalemi eklenmeden önce stok kontrolü yapılabilir.
        // Burada sadece kayıt işlemi örneği verilmiştir:
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
