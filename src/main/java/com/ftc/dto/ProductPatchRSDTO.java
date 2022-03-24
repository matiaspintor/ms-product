package com.ftc.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(
    value = {"sku", "name", "brand", "size", "price", "principalImage", "otherImages"})
public class ProductPatchRSDTO {
  @JsonProperty(value = "sku")
  private String sku;

  @JsonProperty(value = "name")
  private String name;

  @JsonProperty(value = "brand")
  private String brand;

  @JsonProperty(value = "size")
  private String size;

  @JsonProperty(value = "price")
  private Double price;

  @JsonProperty(value = "principalImage")
  private String principalImage;

  @JsonProperty(value = "otherImages")
  private List<String> otherImages;
}
