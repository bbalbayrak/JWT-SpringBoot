package com.yena.webyena.controllers;


import com.yena.webyena.customExceptions.ApplicationException;
import com.yena.webyena.entities.Vendors;
import com.yena.webyena.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;


    @GetMapping(value = "/vendors")
    public ResponseEntity<?> getAllVendors(){
        var vendor = vendorService.getVendors();
        if(vendor == null){
            throw new ApplicationException(
                    "Vendors fetch process failed!",
                    String.format("Vendors fetch process failed!"),
                    HttpStatus.BAD_REQUEST
                    );
        }
        return ResponseEntity.ok(vendor);
    }

    @PostMapping(value = "/vendors")
    public Vendors createVendors(@RequestBody Vendors vendors){
        var vendor = vendorService.createVendor(vendors);
        if(vendor == null){
            throw new ApplicationException(
                    "Vendor creation failed",
                    String.format("Vendor creation failed"),
                    HttpStatus.BAD_REQUEST);
        }
        return vendor;
    }

    @GetMapping(value = "/vendors/{vendorId}")
    public ResponseEntity<?> getVendorById (@PathVariable Integer vendorId){
        var vendor = vendorService.getOneVendor(vendorId);
        if(vendor == null){
            throw new ApplicationException(
                    "Fetching vendor by id failed",
                    String.format("Fetching vendor by id failed"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(vendor);
    }

    @GetMapping(value = "/vendorsByName")
    public Optional<Vendors> getVendorByName(@RequestParam String vendorName) {
        var vendor = vendorService.getVendorsByName(vendorName);
        if(vendor == null){
            throw new ApplicationException(
                    "Fetching vendor by name failed",
                    String.format("Fetching vendor by name failed"),
                    HttpStatus.BAD_REQUEST);
        }
        return vendor;
    }

    @DeleteMapping(value = "/vendors/{vendorId}")
    public void deleteVendorById (@PathVariable Integer vendorId){
        vendorService.deleteVendor(vendorId);
    }

}
