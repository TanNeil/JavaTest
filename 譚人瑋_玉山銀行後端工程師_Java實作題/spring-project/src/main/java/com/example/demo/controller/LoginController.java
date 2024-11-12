package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.LoginService;

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, String password, Model model) {
        
        String authenticationResult = loginService.authenticate(username, password);
        
        if ("user_success".equals(authenticationResult)) {
            
            return "redirect:/list";
        } else if ("帳號或密碼錯誤".equals(authenticationResult)) {
            
            model.addAttribute("error", "帳號或密碼錯誤");
        } else if ("資料庫連線失敗".equals(authenticationResult)) {
            
            model.addAttribute("error", "資料庫連線失敗");
        }
        
        
        return "login";
    }
}
