package com.ftc.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter()
@Setter()
@SuperBuilder()
@AllArgsConstructor()
@NoArgsConstructor()
public class ProductListGetRSDTO extends PageableBaseRSDTO{

  private List<ProductGetRSDTO> products;
}
