package com.example.pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.dto.CheckoutDTO;
import com.example.pos.entity.CheckoutEntity;
import com.example.pos.service.CheckoutService;

@RestController
@CrossOrigin(origins = "*")
public class CheckoutController {
    @Autowired 
    private CheckoutService checkoutService;

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutEntity> createCheckout(@RequestBody CheckoutDTO checkoutDTO){
       CheckoutEntity checkoutEntity = checkoutService.createCheckout(checkoutDTO);

       if(checkoutEntity!=null){
            return ResponseEntity.status(200).body(checkoutEntity);
       }else{
            return ResponseEntity.status(400).body(null);
       }
    }

    @GetMapping("/checkout")
    public ResponseEntity<List<CheckoutEntity>> getAllCheckouts(){
        return ResponseEntity.status(200).body(checkoutService.findAll());
    }

    @GetMapping("/checkout/{id}")
    public ResponseEntity<CheckoutEntity> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(checkoutService.findById(id));
    }

    @DeleteMapping("/checkout/{id}")
    public void deleteCheckout(@PathVariable Long id){
        checkoutService.deleteCheckout(id);
    }
}
