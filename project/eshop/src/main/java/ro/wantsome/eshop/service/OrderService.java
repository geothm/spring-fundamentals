package ro.wantsome.eshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.eshop.domain.Order;
import ro.wantsome.eshop.domain.OrderJpaRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    public OrderService(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Transactional
    public void save(Order order){
        orderJpaRepository.save(order);
    }

    public List<Order> findAll(){
        return orderJpaRepository.findAll();
    }

    public Order findById(Long id){
        return orderJpaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        orderJpaRepository.deleteById(id);
    }
}
