package com.assignment.expensify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorDataRepo extends JpaRepository<VendorEntity, String> {

	
}
