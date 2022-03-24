package com.ftc.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ftc.dto.ProductByIdGetRQDTO;
import com.ftc.dto.ProductGetRSDTO;
import com.ftc.dto.ProductListGetRQDTO;
import com.ftc.dto.ProductListGetRSDTO;
import com.ftc.dto.ProductPatchRQDTO;
import com.ftc.dto.ProductPatchRSDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;
import com.ftc.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping(path = "/products")
@Slf4j
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping()
  public ResponseEntity<ProductPostRSDTO> newProduct(
      @RequestBody() @Valid() ProductPostRQDTO product) {
    log.info("POST: product");
    return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.newProduct(product));
  }

  @GetMapping()
  public ResponseEntity<ProductListGetRSDTO> findAll(
      @RequestParam(required = false, name = "page") Integer page,
      @RequestParam(required = false, name = "size") Integer size, ProductListGetRQDTO productRQ) {
    log.info("GET: product find all");
    return ResponseEntity.status(HttpStatus.OK).body(this.productService.listAll(productRQ));
  }

  @GetMapping(path = {"/{sku}"})
  public ResponseEntity<ProductGetRSDTO> findById(@PathVariable(name = "sku") String sku,
      @Valid ProductByIdGetRQDTO productDTO) {
    log.info("GET: product find by id");
    return ResponseEntity.status(HttpStatus.OK).body(this.productService.findBySku(productDTO));
  }

  @PatchMapping(path = {"/{sku}"})
  public ResponseEntity<ProductPatchRSDTO> update(@PathVariable(name = "sku") String sku,
      @RequestBody() @Valid() ProductPatchRQDTO productDTO) {
    log.info("PATCH: product update");
    return ResponseEntity.status(HttpStatus.OK).body(this.productService.update(sku, productDTO));
  }

  @DeleteMapping(path = {"/{sku}"})
  public ResponseEntity<Void> delete(@PathVariable(name = "sku") String sku) {
    log.info("DELETE: product");
    this.productService.delete(sku);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
