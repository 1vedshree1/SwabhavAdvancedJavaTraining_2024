package com.techlabs.captcha.controller; 
 
import com.techlabs.captcha.service.CaptchaService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
 
@RestController 
@RequestMapping("/captchaapp") 
public class CaptchaController { 
 
    @Autowired 
    private CaptchaService captchaService; 
 
    @GetMapping("/generate") 
    public ResponseEntity<String> generateCaptcha(@RequestParam int width, @RequestParam int height) { 
        String captchaImage = captchaService.generateCaptcha(width, height); 
        return ResponseEntity.ok(captchaImage); 
    } 
 
    @PostMapping("/validate") 
    public ResponseEntity<Boolean> validateCaptcha(@RequestParam String captchaText, @RequestParam String userInput) { 
        boolean isValid = captchaService.validateCaptcha(captchaText, userInput); 
        return ResponseEntity.ok(isValid); 
    } 
}