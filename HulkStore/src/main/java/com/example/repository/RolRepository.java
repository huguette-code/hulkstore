package com.example.repository;

import com.example.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<Role, Long> {
	Role findByName(@Param("name") String name);
}