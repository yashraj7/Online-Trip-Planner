package com.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.code.entity.PackageDetails;
import com.code.exceptions.PackageNotFoundException;
import com.code.repository.IPackageRepository;
import com.code.service.IPackageServiceImpl;

@ExtendWith(SpringExtension.class)
public class TestPackageService {

	public TestPackageService() {
		super();
	}

	@InjectMocks
	private IPackageServiceImpl iPackageService;

	@MockBean
	private IPackageRepository iPackageRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	// result = actual
	// packageDetails = expected

	// Test case for viewing all packages
	@Test
	public void testGetAllPackageDetails() {
		when(iPackageRepository.findAll()).thenReturn(Stream.of(getPackageDetail()).collect(Collectors.toList()));
		assertEquals(1, iPackageService.viewAllPackage().size());
	}

	// Test case for viewing package by Id
	@Test
	public void testSearchPackageDetails() throws PackageNotFoundException {
		PackageDetails packageDetails = getPackageDetail();
		Mockito.when(iPackageRepository.findById(packageDetails.getPackageId()))
				.thenReturn(Optional.of(packageDetails));
		PackageDetails result = iPackageService.searchPackage(packageDetails.getPackageId());
		assertEquals(packageDetails, result, "Correct object is not received");
	}

	// Test case adding package
	@Test
	public void testAddPackage() throws PackageNotFoundException {
		PackageDetails packageDetails = getPackageDetail();
		Mockito.when(iPackageRepository.save(packageDetails)).thenReturn(packageDetails);
		PackageDetails result = iPackageService.addPackage(packageDetails);
		assertEquals(packageDetails, result); // to check whether the objects are equal
	}

	// Test case for removing package by id
	@Test
	public void testRemovePackage() throws PackageNotFoundException {
		PackageDetails packageDetails = getPackageDetail();
		Mockito.when(iPackageRepository.findById(packageDetails.getPackageId()))
				.thenReturn(Optional.of(packageDetails));
		PackageDetails result = iPackageService.deletePackage(packageDetails.getPackageId());
		assertEquals(packageDetails, result);
	}

	// Test case for updating package
	@Test
	public void testUpdatePackage() throws PackageNotFoundException {
		PackageDetails packageDetails = getPackageDetail();
		Mockito.when(iPackageRepository.findById(packageDetails.getPackageId()))
				.thenReturn(Optional.of(packageDetails));
		Mockito.when(iPackageRepository.save(packageDetails)).thenReturn(packageDetails);
		PackageDetails result = iPackageService.modifyPackage(packageDetails);
		assertEquals(packageDetails, result);

	}

	private PackageDetails getPackageDetail() {
		PackageDetails packageDetails = new PackageDetails();
		packageDetails.setPackageId(4);
		packageDetails.setPackageName("Masti");
		packageDetails.setPackageDescription("Two night one day");
		packageDetails.setPackageType("Medium");
		packageDetails.setPackageCost(7000);
		packageDetails.setHotelName("Hotel Dhillon");

		return packageDetails;
	}

}