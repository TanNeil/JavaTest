package com.example.demo.service;

import com.example.demo.entity.Members;
import java.util.Optional;

public interface MembersService {
    
    Members authenticate(String username, String password);
    
    
    Optional<Members> findById(int id);

    Members getCurrentUser();
    
    
    Members registerNewMember(String username, String password);
    
    
    Members updateMember(int id, String username, String password, String email);
}
