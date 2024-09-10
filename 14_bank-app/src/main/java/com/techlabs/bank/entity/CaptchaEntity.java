package com.techlabs.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "captchas")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CaptchaEntity {
	
	@Id
   
	private String id;
	private String image;
	private String answer;

}
