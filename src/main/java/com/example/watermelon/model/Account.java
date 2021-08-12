package com.example.watermelon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @NotEmpty(message = "Không để trống tên đăng nhập")
    private String username;
    @Column(name = "password")
    @NotEmpty(message = "Không để trống mật khẩu")
    private String password;
    @Column(name = "fullname")
    @NotEmpty(message = "Không để trống họ và tên")
    private String fullname;
    @Column(name = "Email")
    @NotEmpty(message = "Không để trống email")
    private String email;
    @Column(name = "image")
    private String image;
    @Column(name = "phonenumber")
    private String phonenumber;
    @Column(name = "admin")
    private Boolean admin;
    @Column(name = "activity")
    private Boolean activity;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")

    private Date birthday;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Order> orders;

    public Account(Long id, String username, String password, String fullname, String email, String image, String phonenumber, Boolean admin, Boolean activity, Boolean gender, Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.image = image;
        this.phonenumber = phonenumber;
        this.admin = admin;
        this.activity = activity;
        this.gender = gender;
        this.birthday = date;
    }

}
