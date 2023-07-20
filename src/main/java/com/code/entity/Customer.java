package com.code.entity;



import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; 



@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int customerId;
	private String name;    
	private String mobileNo;
	
	@NotNull
	@NotBlank(message="Customer emailId is required")
	private String emailId;    
	
	@NotNull
	@NotBlank(message="Customer password is required")
	private String password;
	
	//Toggle variable to check admin logged in or not
	
	private boolean loggedIn;
	
	public Customer() {
		
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	//To check whether Customer exist or not
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer cust = (Customer) o;
        return Objects.equals(emailId, cust.emailId) &&
                Objects.equals(password, cust.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(customerId, emailId, password, 
                            loggedIn);
    }
	
}