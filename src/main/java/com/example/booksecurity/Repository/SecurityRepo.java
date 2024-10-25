package com.example.booksecurity.Repository;

import com.example.booksecurity.Entity.Security;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SecurityRepo extends MongoRepository<Security,Long> {
    public Optional<Security> findByUsername(String username);

}
