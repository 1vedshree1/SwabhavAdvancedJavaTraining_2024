package com.techlabs.bank.service;

import cn.apiclub.captcha.Captcha;

public interface CaptchaService {

	Captcha createCaptcha(int width, int height);

	String encodeCaptcha(Captcha captcha);

	String generateCaptcha(int width, int height);

	boolean validateCaptcha(String userInput);

}
