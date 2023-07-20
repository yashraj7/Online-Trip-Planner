package com.code.service;

import com.code.entity.Admin;
import com.code.entity.Status;

public interface IAdminService {

	public String saveAdmin(Admin admin);

	public Admin getAdminById(Integer id);

	public Admin modifyAdmin(Admin admin);

	public String removeAdminById(Integer id);

	public Status signIn(Admin admin);

	public Status signOut(Admin admin);
}
