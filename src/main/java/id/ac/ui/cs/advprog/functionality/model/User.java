package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
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