package com.ftc.service.impl;

import java.util.Collections;
import java.util.List;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ftc.dto.ProductByIdGetRQDTO;
import com.ftc.dto.ProductGetRSDTO;
import com.ftc.dto.ProductListGetRQDTO;
import com.ftc.dto.ProductListGetRSDTO;
import com.ftc.dto.ProductPatchRQDTO;
import com.ftc.dto.ProductPatchRSDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;
import com.ftc.entity.Product;
import com.ftc.exception.ConflictException;
import com.ftc.exception.NotFoundException;
import com.ftc.repository.ProductRepository;
import com.ftc.service.ProductService;

@Service()
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ProductPostRSDTO newProduct(ProductPostRQDTO productDTO) {
    if (this.productRepository.findBySkuIgnoreCase(productDTO.getSku()).isEmpty()) {
      Product product = this.modelMapper.map(productDTO, Product.class);
      this.productRepository.save(product);
      return this.modelMapper.map(product, ProductPostRSDTO.class);
    }
    throw new ConflictException("The sku is already registered.");
  }

  @Override
  public ProductListGetRSDTO listAll(ProductListGetRQDTO filter) {
    Pageable page = PageRequest.of(0, Integer.MAX_VALUE);
    if (filter.getPage() != null && filter.getSize() != null) {
      page = PageRequest.of(filter.getPage(), filter.getSize());
    }
    Page<Product> pageProducts = this.productRepository.findAll(page);
    return this.convertProductPageToResponse(pageProducts);
  }

  private ProductListGetRSDTO convertProductPageToResponse(Page<Product> pageProducts) {
    List<ProductGetRSDTO> listProducts = this.modelMapper.map(pageProducts.getContent(),
        new TypeToken<List<ProductGetRSDTO>>() {}.getType());
    return ProductListGetRSDTO.builder().currentPage(pageProducts.getNumber())
        .totalItems(pageProducts.getTotalElements()).totalPages(pageProducts.getTotalPages())
        .products(listProducts).build();
  }

  @Override
  public ProductGetRSDTO findBySku(ProductByIdGetRQDTO skuDTO) {
    Product productFinded = this.productRepository.findBySkuIgnoreCase(skuDTO.getSku())
        .orElseThrow(() -> new NotFoundException("The sku is not registered."));
    return this.modelMapper.map(productFinded, ProductGetRSDTO.class);
  }

  @Override
  public ProductPatchRSDTO update(String sku, ProductPatchRQDTO productDTO) {
    Product product = this.productRepository.findBySkuIgnoreCase(sku)
        .orElseThrow(() -> new NotFoundException("The sku is not registered."));
    this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    this.modelMapper.map(productDTO, product);
    if (productDTO.getOtherImages() != null && productDTO.getOtherImages().isEmpty()) {
      product.setOtherImages(Collections.emptyList());
    }
    this.productRepository.save(product);
    return this.modelMapper.map(product, ProductPatchRSDTO.class);
  }

  @Override
  public void delete(String sku) {
    Product product = this.productRepository.findBySkuIgnoreCase(sku)
        .orElseThrow(() -> new NotFoundException("The sku is not registered."));
    this.productRepository.delete(product);
  }

}
