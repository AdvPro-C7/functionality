package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.publisher) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM Book b WHERE " +
        "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(b.publisher) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
        "ORDER BY " +
        "CASE WHEN :sortBy = 'newest' THEN b.publish_date END DESC, " +
        "CASE WHEN :sortBy = 'popularity' THEN b.sold END DESC, " +
        "CASE WHEN :sortBy = 'priceAsc' THEN b.price END ASC, " +
        "CASE WHEN :sortBy = 'priceDesc' THEN b.price END DESC", nativeQuery = true)
    List<Book> searchAndSortBooks(@Param("keyword") String keyword, @Param("sortBy") String sortBy);
}