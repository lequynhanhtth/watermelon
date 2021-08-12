package com.example.watermelon.repository;

import com.example.watermelon.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);


    Page<Account> findByAdmin(Boolean admin, Pageable pageable);

}
