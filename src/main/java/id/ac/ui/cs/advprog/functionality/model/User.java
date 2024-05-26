package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "no_telp"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "nama")
    private String nama;

    @Column(name = "email")
    private String email;

    @Column(name = "no_telp")
    private String noTelp;

    @Column(name= "password")
    private String password;

    public User(){

    }
    public User(String nama, String email, String noTelp, String password) {
        this.nama = nama;
        this.email = email;
        this.noTelp = noTelp;
        this.password = password;
    }
}