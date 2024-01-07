package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.StudentAddress;

public interface StudentAddressRepo extends JpaRepository<StudentAddress, Long>{

}
