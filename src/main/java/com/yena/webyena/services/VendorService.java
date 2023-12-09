package com.yena.webyena.services;


import com.yena.webyena.entities.Vendors;
import com.yena.webyena.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    //GET ALL VENDORS
    public List<Vendors> getVendors(){
        return vendorRepository.findAll();
    }

    //GET VENDORS BY ID
    public Optional<Vendors> getOneVendor(Integer vendorId) {
        return vendorRepository.findById(vendorId);
    }

    //POST VENDOR
    public Vendors createVendor(Vendors vendor) {
        return vendorRepository.saveAndFlush(vendor);
    }

    //GET VENDOR BY NAME
    public Optional<Vendors> getVendorsByName(String vendorName){
        return vendorRepository.findByName(vendorName);
    }

    //DELETE VENDOR BY ID
    public void deleteVendor(Integer vendorId){
        vendorRepository.deleteById(vendorId);
    }


}
