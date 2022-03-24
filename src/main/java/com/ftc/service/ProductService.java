package com.ftc.service;

import com.ftc.dto.ProductListGetRQDTO;
import com.ftc.dto.ProductListGetRSDTO;
import com.ftc.dto.ProductPatchRQDTO;
import com.ftc.dto.ProductPatchRSDTO;
import com.ftc.dto.ProductByIdGetRQDTO;
import com.ftc.dto.ProductGetRSDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;

public interface ProductService {

  ProductPostRSDTO newProduct(ProductPostRQDTO productDTO);

  ProductListGetRSDTO listAll(ProductListGetRQDTO filter);

  ProductGetRSDTO findBySku(ProductByIdGetRQDTO skuDTO);

  ProductPatchRSDTO update(String sku, ProductPatchRQDTO productDTO);

  void delete(String sku);

}
