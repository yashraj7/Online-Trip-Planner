package com.code.service;

import java.util.List;

import com.code.entity.PackageDetails;
import com.code.exceptions.PackageNotFoundException; 

public interface IPackageService {
	
	
	public  PackageDetails  addPackage(PackageDetails pack)throws PackageNotFoundException;
	public  PackageDetails  deletePackage(int packageId) throws PackageNotFoundException;
	public  PackageDetails  searchPackage(int packageId) throws PackageNotFoundException;
	public  List<PackageDetails> viewAllPackage();
	public  PackageDetails  modifyPackage(PackageDetails pack) throws PackageNotFoundException;
	

}