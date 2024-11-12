package com.example.demo.service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.MembersDAO;
import com.example.demo.entity.Members;

@Service
public class LoginService {

    @Autowired
    private MembersDAO membersdao;

    @Autowired
    private HttpSession session;

    
    public String authenticate(String username, String password) {
        
        Members member = membersdao.findByUsernameAndPassword(username, password);
        if (member != null) {
            session.setAttribute("currentUser", member);
            return "user_success"; 
        } else {
            return "帳號或密碼錯誤";
        }
    }
}
