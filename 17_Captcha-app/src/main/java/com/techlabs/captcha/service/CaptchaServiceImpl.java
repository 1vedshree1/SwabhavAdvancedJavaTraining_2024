package com.techlabs.captcha.service; 
 
import com.techlabs.captcha.entity.CaptchaEntity; 
import com.techlabs.captcha.repository.CaptchaRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
 
import java.io.ByteArrayOutputStream; 
import java.io.IOException; 
import java.util.Base64; 
import java.util.Optional; 
import java.util.concurrent.ConcurrentHashMap; 
import javax.imageio.ImageIO; 
import cn.apiclub.captcha.Captcha; 
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer; 
import cn.apiclub.captcha.gimpy.FishEyeGimpyRenderer; 
import cn.apiclub.captcha.text.producer.DefaultTextProducer; 
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer; 
 
@Service 
public class CaptchaServiceImpl implements CaptchaService { 
 
    @Autowired 
    private CaptchaRepository captchaRepository; 
 
    public Captcha createCaptcha(int width, int height) {
        return new Captcha.Builder(width, height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(), new DefaultWordRenderer())
                .gimp(new FishEyeGimpyRenderer())
                .addNoise()
                .build();
    }

 
    public String encodeCaptcha(Captcha captcha) { 
        String image = null; 
        try { 
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
            ImageIO.write(captcha.getImage(), "jpg", bos); 
            byte[] byteArray = Base64.getEncoder().encode(bos.toByteArray()); 
            image = new String(byteArray); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return image; 
    } 
 
    public String generateCaptcha(int width, int height) { 
        Captcha captcha = createCaptcha(width, height); 
        String captchaText = captcha.getAnswer(); 
        String encodedImage = encodeCaptcha(captcha); 
 
        
        CaptchaEntity captchaEntity = new CaptchaEntity(); 
        captchaEntity.setId(captchaText); 
        captchaEntity.setImage(encodedImage); 
        captchaEntity.setAnswer(captchaText); 
        captchaRepository.save(captchaEntity); 
 
        return encodedImage; 
    } 
 
    public boolean validateCaptcha(String captchaText, String userInput) { 
        Optional<CaptchaEntity> captchaEntity = captchaRepository.findById(captchaText); 
        return captchaEntity.isPresent() && captchaEntity.get().getAnswer().equals(userInput); 
    } 
}