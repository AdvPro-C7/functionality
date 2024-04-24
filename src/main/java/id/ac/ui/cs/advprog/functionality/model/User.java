package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


public class User {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String username;


    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String role;

}
