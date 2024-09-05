package com.techlabs.captcha.service;

import cn.apiclub.captcha.Captcha;

public interface CaptchaService {

	Captcha createCaptcha(int width, int height);

	String encodeCaptcha(Captcha captcha);

	String generateCaptcha(int width, int height);

	boolean validateCaptcha(String captchaText, String userInput);

}
