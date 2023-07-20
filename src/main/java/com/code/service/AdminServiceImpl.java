package com.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entity.Admin;
import com.code.entity.Status;
import com.code.exceptions.AdminNotFoundException;
import com.code.repository.IAdminRepo;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepo repo;

	@Override
	public String saveAdmin(Admin admin) {
		Admin user = repo.save(admin);
		try {
			if (user != null) {
				return "Admin Registered Successfully";
			} else {
				throw new AdminNotFoundException("Admin Cannot be added :: Server Error");
			}
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@Override
	public Admin getAdminById(Integer id) {
		try {
			return repo.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin Id Not found"));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Admin modifyAdmin(Admin admin) {
		Optional<Admin> admin1 = repo.findById(admin.getAdminId());
		try {
			if (admin1.isPresent())
				repo.save(admin);
			else
				throw new AdminNotFoundException("Admin With Id : " + admin.getAdminId() + " doesn't exist");
		} catch (Exception e) {
			e.getMessage();
		}
		return admin;
	}

	@Override
	public String removeAdminById(Integer id) {
		Optional<Admin> admin = repo.findById(id);
		try {
			if (admin.isPresent()) {
				repo.deleteById(id);
				return "Admin with Id : " + id + " deleted successfully";
			} else {
				throw new AdminNotFoundException("Admin With Id : " + id + " doesn't exist");
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@Override
	public Status signIn(Admin admin) {
		List<Admin> users = repo.findAll();
		for (Admin user : users) {
			if (user.equals(admin)) {
				admin.setLoggedIn(true);
				repo.save(admin);
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}

	@Override
	public Status signOut(Admin admin) {
		List<Admin> users = repo.findAll();
		for (Admin user : users) {
			if (user.equals(admin)) {
				admin.setLoggedIn(false);
				repo.save(admin);
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}

}
