package com.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.code.entity.Feedback;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

	@Query("select f from Feedback f where f.customer.customerId=:customerId")
	List<Feedback> findFeedbackByCustId(int customerId);
}