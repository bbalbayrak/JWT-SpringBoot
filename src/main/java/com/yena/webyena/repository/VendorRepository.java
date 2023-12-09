package com.yena.webyena.repository;

import com.yena.webyena.entities.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendors,Integer> {
    Optional<Vendors>findByName(String name);
}
