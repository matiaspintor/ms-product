package com.ftc.dto;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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
public class ProductPatchRQDTO {

  @JsonProperty(value = "name")
  @Size(min = 3, max = 50, message = "The [name] field must contain between 3 and 50 characters.")
  private String name;

  @JsonProperty(value = "brand")
  @Size(min = 3, max = 50, message = "The [brand] field must contain between 3 and 50 characters.")
  private String brand;

  @JsonProperty(value = "size")
  private String size;

  @JsonProperty(value = "price")
  @Min(value = 1, message = "The field [price] must have a minimum value of 1.00")
  @Max(value = 99999999, message = "The field [price] must have a maximum value of 99999999.00")
  private Double price;

  @JsonProperty(value = "principalImage")
  private String principalImage;

  @JsonProperty(value = "otherImages")
  private List<String> otherImages;
}
