package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.Admin;
import com.code.entity.Status;
import com.code.service.IAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService service;

	@PostMapping("/register")
	public String registerAdmin(@Valid @RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}

	@GetMapping("/get/{adminId}")
	public Admin getAdmin(@PathVariable Integer adminId) {
		return service.getAdminById(adminId);
	}

	@PutMapping("/modify")
	public Admin modifyAdmin(@Valid @RequestBody Admin admin) {
		return service.modifyAdmin(admin);
	}

	@DeleteMapping("/delete/{adminId}")
	public String removeAdmin(@PathVariable Integer adminId) {
		return service.removeAdminById(adminId);
	}

	// admin login

	@PostMapping("/login")
	public Status signIn(@Valid @RequestBody Admin admin) {
		return service.signIn(admin);
	}

	// admin logout

	@PostMapping("/logout")
	public Status signOut(@Valid @RequestBody Admin admin) {
		return service.signOut(admin);
	}

}
