package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entity.PackageDetails;

public interface IPackageRepository extends JpaRepository<PackageDetails, Integer> {
}