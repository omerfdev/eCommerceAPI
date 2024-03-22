import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderCode(generateOrderCode());
        order.setCustomer(orderDTO.getCustomer());

        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + itemDTO.getProductId() + " not found"));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product with ID " + itemDTO.getProductId());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setPriceAtOrder(product.getPrice());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order);
            
            order.getOrderItems().add(orderItem);

            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);
        }

        order.setOrderDate(new Date());

        double totalPrice = calculateTotalPrice(order);
        order.setTotalPrice(totalPrice);

        order = orderRepository.save(order);

        return OrderMapper.toDTO(order);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return OrderMapper.toDTO(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrdersForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void placeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found"));

   
        order.setStatus(OrderStatus.CONFIRMED);     
        order.setOrderDate(new Date());     
        orderRepository.save(order);

        order.getOrderItems().forEach(item -> {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
        });

        // Stoğun güncellenmiş ürünleri veritabanına kaydet
        productRepository.saveAll(order.getOrderItems().stream()
                .map(OrderItem::getProduct)
                .collect(Collectors.toList()));
    }

    private String generateOrderCode() {
        return "ORD-" + System.currentTimeMillis();
    }

    private double calculateTotalPrice(Order order) {
        double totalPrice = 0;
        for (OrderItem item : order.getOrderItems()) {
            totalPrice += item.getPriceAtOrder() * item.getQuantity();
        }
        return totalPrice;
    }
}
