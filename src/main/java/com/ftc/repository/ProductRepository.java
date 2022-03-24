package com.ftc.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.ftc.entity.Product;

@Repository()
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

  Page<Product> findAll(Pageable page);
  
  Optional<Product> findBySkuIgnoreCase(String sku);
  
}
