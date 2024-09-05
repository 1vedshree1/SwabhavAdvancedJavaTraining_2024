package com.techlabs.captcha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.captcha.entity.CaptchaEntity;

public interface CaptchaRepository extends JpaRepository<CaptchaEntity, String>{

}
