package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.PackageDetails;
import com.code.exceptions.PackageNotFoundException;
import com.code.service.IPackageService;

import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
public class PackageController {
	
	 @Autowired
	 IPackageService iPackageService;
	
	@GetMapping("/welcome")
	public String home() {
		return "Welcome ! to this trip & choose your package";
	}
	
	//adding a package
	
		@PostMapping("/addPackage")
		public  ResponseEntity<?>  addPackages(@RequestBody @Valid PackageDetails pack) {
			try {
				iPackageService.addPackage(pack);
				System.out.println("Package added successfully");
				return  new ResponseEntity<PackageDetails>(pack, HttpStatus.OK);
			}catch(PackageNotFoundException e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
			}				
		}
	
	//For viewing all the packages
		
	@GetMapping("/getPackages")
	public  List<PackageDetails> viewAllPackages(){
		return this.iPackageService.viewAllPackage();
	}
	
	//for searching packages
	
	@GetMapping("/getPackage/{packageId}")
	public ResponseEntity searchPackage(@PathVariable int packageId) {
		PackageDetails pack = null;
		try {
			pack = this.iPackageService.searchPackage(packageId);
			 return new ResponseEntity<PackageDetails>(pack, HttpStatus.OK);
		}catch(PackageNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND); 
		}
	}
	
	//for deleting packages
	
	@DeleteMapping("/deletePackage/{packageId}")
	public ResponseEntity<?> deletePackage(@PathVariable int packageId){
		try {
			PackageDetails pack = this.iPackageService.deletePackage(packageId);
			return new ResponseEntity<PackageDetails>(pack, HttpStatus.OK);
			
		}catch(PackageNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	//for updating package
	
	@PutMapping("/updatePackage")
	public ResponseEntity<?> updatingPackage(@RequestBody PackageDetails pack){
		try {
			this.iPackageService.modifyPackage(pack);
			return new ResponseEntity<PackageDetails>(pack, HttpStatus.OK);
		}catch(PackageNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}