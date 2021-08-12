package com.example.watermelon.service;

import com.example.watermelon.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<Account> findAll();


    public void save(Account account);

    public void delete(long id);

    public Optional<Account> findById(long id);

    public Optional<Account> findByUsername(String username);

    public Page<Account> findByAdmin(boolean admin, Pageable pageable);
}
