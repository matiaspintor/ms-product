package com.ftc.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ftc.dto.ProductPatchRQDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.service.ProductService;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {

  @InjectMocks()
  private ProductController productController;
  
  @Mock()
  private ProductService productService;
  
  private MockMvc mockMvc;
  
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
  }
  
  @Test()
  void testNewProductHappyCase() throws Exception {
    ProductPostRQDTO product = ProductPostRQDTO.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Baltoro Aro 29")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/products")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(product));

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print());

  }
  
  @Test()
  void testNewProductBadRequest() throws Exception {
    ProductPostRQDTO product = ProductPostRQDTO.builder()
        .sku(null)
        .name(null)
        .brand("Jeep")
        .size(null)
        .price(null)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/products")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(product));

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andDo(MockMvcResultHandlers.print());

  }
  
  @Test()
  void testFindAllHappyCase() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/products")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON);
    
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
    .andDo(MockMvcResultHandlers.print());
  }
  
  @Test()
  void testFindAllHappyCaseWithPage() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/products")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON)
        .queryParam("page", "0")
        .queryParam("size", "10");
    
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
    .andDo(MockMvcResultHandlers.print());
  }
  
  @Test()
  void testFindByIdHappyCase() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/products/FAL-881952283")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON);
    
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
    .andDo(MockMvcResultHandlers.print());
  }
  
  @Test()
  void testUpdateHappyCase() throws Exception {
    ProductPatchRQDTO product = ProductPatchRQDTO.builder()
        .name("Bicicleta Baltoro Aro 29 - modify")
        .brand("Jeep")
        .size("ST")
        .price(399990.00)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/products/FAL-881952283")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(product));
    
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
    .andDo(MockMvcResultHandlers.print());
  }
  
  @Test()
  void testUpdateBadRequest() throws Exception {
    ProductPatchRQDTO product = ProductPatchRQDTO.builder()
        .name("Bicicleta Baltoro Aro 29 - modify")
        .brand("Jeep")
        .size("ST")
        .price(.0)
        .principalImage("https://falabella.scene7.com/is/image/Falabella/881952283_2")
        .build();
    
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/products/FAL-881952283")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(product));
    
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest())
    .andDo(MockMvcResultHandlers.print());
  }
  
  @Test()
  void testDeleteHappyCase() throws Exception {  
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/products/FAL-881952283")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON);
    
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNoContent())
    .andDo(MockMvcResultHandlers.print());
  }
  
  private String asJsonString(Object obj) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      ObjectWriter objectWriter = mapper.writer();
      return objectWriter.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
