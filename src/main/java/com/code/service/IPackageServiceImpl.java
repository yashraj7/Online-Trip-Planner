package com.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entity.PackageDetails;
import com.code.exceptions.PackageNotFoundException;
import com.code.repository.IPackageRepository;

@Service
public class IPackageServiceImpl implements IPackageService {

	@Autowired
	public IPackageRepository iPackageRepository;

	public IPackageServiceImpl() {
	}

	@Override
	public PackageDetails addPackage(PackageDetails pack) throws PackageNotFoundException {
		PackageDetails packet = null;
		if (pack.getPackageCost() > 0) {
			packet = iPackageRepository.save(pack);
			return packet;
		} else
			throw new PackageNotFoundException("Cannot add package, may be Cost is less than or equal to zero");
	}

	@Override
	public PackageDetails deletePackage(int packageId) throws PackageNotFoundException {
		Optional<PackageDetails> optional = iPackageRepository.findById(packageId);
		PackageDetails packet = optional.get();
		if (packet == null)
			throw new PackageNotFoundException("Package Not found");

		iPackageRepository.deleteById(packageId);
		return packet;
	}

	@Override
	public PackageDetails searchPackage(int packageId) throws PackageNotFoundException {
		PackageDetails pack = iPackageRepository.findById(packageId)
				.orElseThrow(() -> new PackageNotFoundException("This package does not exist"));
		return pack;
	}

	@Override
	public List<PackageDetails> viewAllPackage() {
		List<PackageDetails> list = iPackageRepository.findAll();
		return list;
	}

	@Override
	public PackageDetails modifyPackage(PackageDetails pack) throws PackageNotFoundException {
		Optional<PackageDetails> optional = iPackageRepository.findById(pack.getPackageId());

		if (optional.isPresent()) {
			iPackageRepository.save(pack);
		} else
			throw new PackageNotFoundException("Pakage Id not found");

		return pack;
	}

}