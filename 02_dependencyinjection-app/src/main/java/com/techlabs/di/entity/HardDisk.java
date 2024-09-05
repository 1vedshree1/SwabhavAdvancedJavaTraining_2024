package com.techlabs.di.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class HardDisk {
	
	private int capacity;

	
	public HardDisk() {
		
	}
	@Autowired
	public HardDisk(@Value("10") int capacity) {
		super();
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "HardDisk [capacity=" + capacity + "]";
	}
	

}
