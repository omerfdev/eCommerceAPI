public class OrderItemMapper {
    public static OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setPriceAtOrder(orderItem.getPriceAtOrder());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }
    
    public static OrderItem toEntity(OrderItemDTO dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        
        Order order = new Order();
        order.setId(dto.getOrderId());
        orderItem.setOrder(order);
        
        Product product = new Product();
        product.setId(dto.getProductId());
        orderItem.setProduct(product);
        
        orderItem.setPriceAtOrder(dto.getPriceAtOrder());
        orderItem.setQuantity(dto.getQuantity());
        
        return orderItem;
    }
}
