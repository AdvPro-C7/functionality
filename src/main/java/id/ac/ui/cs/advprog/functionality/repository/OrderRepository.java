package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndStatus(Long userId, OrderStatus status);
    List<Order> findAllByUserIdAndStatus(Long userId, OrderStatus status);
}
