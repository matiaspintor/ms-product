package com.ftc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ftc.dto.ProductByIdGetRQDTO;
import com.ftc.dto.ProductGetRSDTO;
import com.ftc.dto.ProductListGetRQDTO;
import com.ftc.dto.ProductListGetRSDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;
import com.ftc.entity.Product;
import com.ftc.exception.ConflictException;
import com.ftc.exception.NotFoundException;
import com.ftc.repository.ProductRepository;
import com.ftc.service.impl.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

  @InjectMocks()
  private ProductServiceImpl productService;
  
  @Mock()
  private ProductRepository productRepository;
  
  @Spy()
  private ModelMapper modelMapper;
  
  @Test()
  void testNewProductHappyCase() {
    ProductPostRQDTO product = ProductPostRQDTO.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Baltoro Aro 29")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    
    doReturn(Optional.empty()).when(this.productRepository).findBySkuIgnoreCase(Mockito.anyString());
    
    ProductPostRSDTO result = this.productService.newProduct(product);
    assertNotNull(result);
    assertEquals("FAL-881952283", result.getSku());
    assertEquals("Bicicleta Baltoro Aro 29", result.getName());
    assertEquals("Jeep", result.getBrand());
    assertEquals("ST", result.getSize());
    assertEquals(399990.00, result.getPrice());
    assertNull(result.getOtherImages());
    assertNotNull(result.getPrincipalImage());
  }
  
  @Test()
  void testNewProductConflict() {
    ProductPostRQDTO product = ProductPostRQDTO.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Baltoro Aro 29")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    
    doReturn(Optional.of(Product.builder().sku("FAL-881952283").build()))
      .when(this.productRepository).findBySkuIgnoreCase(Mockito.anyString());
    
   assertThrows(ConflictException.class, ()->{
     this.productService.newProduct(product);
   });
  }
  
  @Test()
  void testListAllNotFilters() {
    Product productRegistered = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Baltoro Aro 29")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    Page<Product> pageProducts = new PageImpl<Product>(
        Arrays.asList(productRegistered));
    doReturn(pageProducts).when(this.productRepository).findAll((Pageable) Mockito.any());
    
    ProductListGetRSDTO result = this.productService.listAll(ProductListGetRQDTO.builder().build());
    assertNotNull(result);
    assertFalse(result.getProducts().isEmpty());
    assertEquals(1, result.getTotalItems());
    assertEquals(1, result.getTotalPages());
    assertEquals(0, result.getCurrentPage());
  }
  
  @Test()
  void testListAllWithFilters() {
    Product productRegistered = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Baltoro Aro 29")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    Page<Product> pageProducts = new PageImpl<Product>(
        Arrays.asList(productRegistered));
    doReturn(pageProducts).when(this.productRepository).findAll((Pageable) Mockito.any());
    
    ProductListGetRSDTO result = this.productService.listAll(ProductListGetRQDTO.builder().page(0).size(2).build());
    assertNotNull(result);
    assertFalse(result.getProducts().isEmpty());
    assertEquals(1, result.getTotalItems());
    assertEquals(1, result.getTotalPages());
    assertEquals(0, result.getCurrentPage());
  }
  
  @Test()
  void testFindBySkuHappyCase() {
    ProductByIdGetRQDTO skuDTO = ProductByIdGetRQDTO.builder()
        .sku("FAL-881952283")
        .build();
    
    Product productRegistered = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Baltoro Aro 29")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    
    doReturn(Optional.of(productRegistered)).when(this.productRepository).findBySkuIgnoreCase(Mockito.anyString());
    
    ProductGetRSDTO result = this.productService.findBySku(skuDTO);
    assertNotNull(result);
    assertEquals("FAL-881952283", result.getSku());
    assertEquals("Bicicleta Baltoro Aro 29", result.getName());
    assertEquals("Jeep", result.getBrand());
    assertEquals("ST", result.getSize());
    assertEquals(399990.00, result.getPrice());
    assertNull(result.getOtherImages());
    assertNotNull(result.getPrincipalImage());
    
  }
  
  @Test()
  void testFindBySkuNotFound() {
    ProductByIdGetRQDTO skuDTO = ProductByIdGetRQDTO.builder()
        .sku("FAL-881952283")
        .build();
    
    doReturn(Optional.empty()).when(this.productRepository).findBySkuIgnoreCase(Mockito.anyString());
    
    assertThrows(NotFoundException.class, ()->{
      this.productService.findBySku(skuDTO);    
    });

  }
}
