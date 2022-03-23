package com.ftc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftc.dto.ProductByIdGetRQDTO;
import com.ftc.dto.ProductDeleteRQDTO;
import com.ftc.dto.ProductGetRSDTO;
import com.ftc.dto.ProductListGetRQDTO;
import com.ftc.dto.ProductPatchRQDTO;
import com.ftc.dto.ProductPatchRSDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;
import com.ftc.repository.ProductRepository;
import com.ftc.service.ProductService;

@Service()
public class ProductServiceImpl{
  
  @Autowired
  private ProductRepository productRepository;

}
