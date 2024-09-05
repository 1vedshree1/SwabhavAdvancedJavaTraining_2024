package com.techlabs.batch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="employees")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Employee {
	
	@Column
	@Id
	private int employeeId;
	@Column
	private String name;
	@Column
	private Double salary;

}
