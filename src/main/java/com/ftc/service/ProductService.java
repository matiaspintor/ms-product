package com.ftc.service;

import java.util.List;
import com.ftc.dto.ProductListGetRQDTO;
import com.ftc.dto.ProductPatchRQDTO;
import com.ftc.dto.ProductPatchRSDTO;
import com.ftc.dto.ProductByIdGetRQDTO;
import com.ftc.dto.ProductDeleteRQDTO;
import com.ftc.dto.ProductGetRSDTO;
import com.ftc.dto.ProductPostRQDTO;
import com.ftc.dto.ProductPostRSDTO;

public interface ProductService {

  ProductPostRSDTO newProduct(ProductPostRQDTO product);

  List<ProductGetRSDTO> listAll(ProductListGetRQDTO filter);

  ProductGetRSDTO findById(ProductByIdGetRQDTO productId);

  ProductPatchRSDTO update(ProductPatchRQDTO product);

  void delete(ProductDeleteRQDTO productId);

}
