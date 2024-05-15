package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "no_telp"),
})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 128)
    private String nama;

    @NotBlank
    @Size(max = 256)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 64)
    @Column(name = "no_telp", unique = true)
    private String noTelp;

    @NotBlank
    @Size(max = 128)
    private String password;

    @Lob
    private byte[] foto;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    private String bio;

    @Column(name = "jumlah_peringatan")
    private int jumlahPeringatan;

    private String role;

    public User(String nama, String email, String noTelp, String password) {
        this.nama = nama;
        this.email = email;
        this.noTelp = noTelp;
        this.password = password;
        this.role = "pelanggan";
    }
}