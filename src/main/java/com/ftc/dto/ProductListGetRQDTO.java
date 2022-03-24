package com.ftc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ProductListGetRQDTO {
  @JsonProperty(value = "size")
  private Integer size;

  @JsonProperty(value = "page")
  private Integer page;
}
