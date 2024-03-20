import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        // Sipariş verildiğinde, siparişin alındığı tarih ve diğer detaylar ayarlanabilir.
        // Ayrıca, siparişin toplam fiyatı hesaplanıp kaydedilebilir.
        // Burada örnek bir işlem:
        order.setOrderDate(new Date());
        // Siparişin toplam fiyatını hesaplamak için sipariş kalemlerini dolaşabiliriz.
        double totalPrice = 0;
        //order.getOrderItems.stream().
        for (OrderItem item : order.getOrderItems()) {
            totalPrice += item.getPriceAtOrder() * item.getQuantity();
        }
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
