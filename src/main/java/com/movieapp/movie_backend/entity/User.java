package com.movieapp.movie_backend.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name= "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
    @Column(name="password", nullable=false)
    private String password;
    @Column(name="proflie_picture", length = 255)
    private String profilePicture;
    @Column(name="bio")
    private String bio;
    @Column(name= "created_at", updatable = false)
    private LocalDateTime createdAt;
    public User(){

    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
