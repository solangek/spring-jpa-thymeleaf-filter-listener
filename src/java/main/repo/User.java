package main.repo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    private String email;

    public User() {}

    public User(String userName, String email) {
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

