package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "username", updatable = false, nullable = false, unique = true)
    @Getter
    @Setter
    private String username;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "role")
    @Getter
    @Setter
    private String role;

}
