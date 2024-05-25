package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.dto.AddBookCartDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.CartItems;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import id.ac.ui.cs.advprog.functionality.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

}