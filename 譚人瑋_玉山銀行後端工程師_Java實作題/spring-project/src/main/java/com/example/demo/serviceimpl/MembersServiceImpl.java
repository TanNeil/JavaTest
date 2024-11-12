package com.example.demo.serviceimpl;

import com.example.demo.dao.MembersDAO;
import com.example.demo.entity.Members;
import com.example.demo.service.MembersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersDAO membersDAO;
    
    @Autowired
    private HttpSession session;

    
    
    @Override
    public Members authenticate(String username, String password) {
        return membersDAO.findByUsernameAndPassword(username, password);
    }

    
    @Override
    public Optional<Members> findById(int id) {
        return membersDAO.findById(id);
    }

    
    @Override
    public Members registerNewMember(String username, String password) {
        Members newMember = new Members();
        newMember.setUsername(username);
        newMember.setPassword(password);
        return membersDAO.save(newMember);
    }

    
    @Override
    public Members updateMember(int id, String username, String password, String email) {
        Optional<Members> existingMember = membersDAO.findById(id);
        if (existingMember.isPresent()) {
            Members member = existingMember.get();
            member.setUsername(username);
            member.setPassword(password);
            member.setEmail(email);
            return membersDAO.save(member);
        }
        return null;
    }

    @Override
    public Members getCurrentUser() {
        
        return (Members) session.getAttribute("currentUser");
    }

    public void setCurrentUser(Members member) {
        
        session.setAttribute("currentUser", member);
    }
}
