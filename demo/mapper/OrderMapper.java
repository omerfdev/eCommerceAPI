import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderCode(order.getOrderCode());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setOrderItems(order.getOrderItems().stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toList()));
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }
    
    public static Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());        
        // Burada customer nesnesi atanması
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        order.setCustomer(customer);        
        // Burada orderItems listesi atanması
        List<OrderItem> orderItems = dto.getOrderItems().stream()
                .map(OrderItemMapper::toEntity)
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);        
        order.setOrderCode(dto.getOrderCode());
        return order;
    }
}
