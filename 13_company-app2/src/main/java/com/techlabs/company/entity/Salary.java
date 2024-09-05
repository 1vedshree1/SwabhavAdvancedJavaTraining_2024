package com.techlabs.company.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="salaries")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Salary {
	@Column(name="salaryId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryId;
	@Column(name="salaryMonth")
	private SalaryMonthValues SalaryMonth;
	@Column(name="grossSalary")
	private double grossSalary;
	@Column(name="deductions")
	private double deductions;
	@Column(name="netSalary")
	private double netSalary;
	@Column(name="paymentDate")
	private Date paymentDate;
	@Column(name="SalaryStatus")
	private salaryStatusType Salarystatus;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="transactionId")
	private Transaction transaction;
	
	
	
	

}
