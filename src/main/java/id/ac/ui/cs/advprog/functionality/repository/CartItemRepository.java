package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {
    Optional<CartItems> findByBookIdAndOrderIdAndUserId(int bookId, Long orderId, Long userId);
}