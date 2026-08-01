package com.movieapp.movie_backend.repository;
import com.movieapp.movie_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository  extends JpaRepository<User, Integer>{
Optional<User> findByEmail(String email);
}
