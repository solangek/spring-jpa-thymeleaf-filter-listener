package main.repo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    public UserInfo() {}

    public UserInfo(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", email=" + email + '}';
    }
}

