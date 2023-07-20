package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.entity.Admin;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Integer> {
}
