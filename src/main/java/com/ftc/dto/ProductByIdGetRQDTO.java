package com.ftc.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter()
@Setter()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class ProductByIdGetRQDTO {

  @NotNull(message = "The field [sku] cannot be null.")
  @NotEmpty(message = "The field [sku] cannot be blank.")
  private String sku;
}
