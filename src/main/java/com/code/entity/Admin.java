package com.code.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
//@Data
//@AllArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer AdminId;

	private String contactNumber;

	@NotNull(message = "name can't be empty")
	private String name;

	@NotNull(message = "email can't be empty")
	private String email;

	@NotNull(message = "password can't be empty")
	@Size(min = 8, max = 25, message = "password size between 8 to 25")
	private String password;

	private boolean isLoggedIn;

	public void setLoggedIn(boolean loggedIn) {
		this.isLoggedIn = loggedIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(AdminId, email, password, isLoggedIn);
	}

	public Admin() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Admin))
			return false;
		Admin admin = (Admin) o;
		return Objects.equals(email, admin.email) && Objects.equals(password, admin.password);
	}

	public Admin(Integer adminId, String contactNumber, @NotNull(message = "name can't be empty") String name,
			@NotNull(message = "email can't be empty") String email,
			@NotNull(message = "password can't be empty") @Size(min = 8, max = 25, message = "password size between 8 to 25") String password,
			boolean isLoggedIn) {
		super();
		AdminId = adminId;
		this.contactNumber = contactNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
	}

	public Integer getAdminId() {
		return AdminId;
	}

	public void setAdminId(Integer adminId) {
		AdminId = adminId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	@Override
	public String toString() {
		return "Admin [AdminId=" + AdminId + ", contactNumber=" + contactNumber + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", isLoggedIn=" + isLoggedIn + "]";
	}

}
