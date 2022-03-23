package com.ftc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;
import com.ftc.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping(path = "/product")
@Slf4j
public class ProductController {
  
  @Autowired
  private ProductService productService;
  
  @PostMapping()
  public ResponseEntity<ProductPostRSDTO> newProduct(ProductPostRQDTO product){
    
    log.info("POST: product");
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

}
