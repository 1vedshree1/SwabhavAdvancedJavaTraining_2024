package com.techlabs.bank.controller; 
 
import com.techlabs.bank.service.CaptchaService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
 
@RestController 
@RequestMapping("/bankapp") 
public class CaptchaController { 
 
    @Autowired 
    private CaptchaService captchaService; 
 
    @GetMapping("/captcha") 
    public ResponseEntity<String> generateCaptcha(@RequestParam int width, @RequestParam int height) { 
        String captchaImage = captchaService.generateCaptcha(width, height); 
        return ResponseEntity.ok(captchaImage); 
    } 
 
   
}