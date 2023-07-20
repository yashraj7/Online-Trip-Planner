package com.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.code.entity.Admin;
import com.code.exceptions.AdminNotFoundException;
import com.code.repository.IAdminRepo;
import com.code.service.IAdminService;

@SpringBootTest
class AdminTest {

	@Autowired
	IAdminService adminservice;

	@MockBean
	IAdminRepo adminRepository;

	private Admin getAdmin() {
		Admin admin = new Admin();

		admin.setAdminId(5);
		admin.setName("Neha");
		admin.setContactNumber("7972794130");
		admin.setEmail("neha@gmail.com");
		admin.setPassword("Pa@123");

		return admin;
	}

	@Test
	void testAddAdmin() throws AdminNotFoundException {
		Admin admin = getAdmin();
		when(adminRepository.save(admin)).thenReturn(admin);
		assertEquals(adminservice.saveAdmin(admin), "Admin Added successfully");
	}

	@Test
	void testViewAdmin() throws AdminNotFoundException {
		Admin admin = getAdmin();

		when(adminRepository.findById(5)).thenReturn(Optional.of(admin));
		assertThat(adminservice.getAdminById(5)).isEqualTo(admin);
	}

	@Test
	void testUpdateAdmin() throws AdminNotFoundException {
		Admin admin = getAdmin();

		when(adminRepository.findById(5)).thenReturn(Optional.of(admin));
		admin.setName("abc");
		when(adminRepository.save(admin)).thenReturn(admin);
		assertThat(adminservice.modifyAdmin(admin)).isEqualTo(admin);
	}

	@Test
	void testDeleteAdmin() throws AdminNotFoundException {
		Admin admin = getAdmin();
		when(adminRepository.findById(5)).thenReturn(Optional.of(admin));
		adminservice.removeAdminById(5);
		verify(adminRepository, times(1)).deleteById(5);
	}

}