package com.example.demo.dao;

import com.example.demo.entity.Members;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersDAO extends JpaRepository<Members, Integer> {
    Members findByUsernameAndPassword(String username, String password);
    Optional<Members> findById(Long id);
    void deleteById(Long id);
    boolean existsByUsername(String username);
    
}
