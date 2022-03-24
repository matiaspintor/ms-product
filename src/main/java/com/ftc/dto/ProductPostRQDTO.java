package com.ftc.dto;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class ProductPostRQDTO {

  @JsonProperty(value = "sku")
  @NotNull(message = "The field [sku] cannot be null.")
  @NotBlank(message = "The field [sku] cannot be blank.")
  private String sku;

  @JsonProperty(value = "name")
  @NotNull(message = "The field [name] cannot be null.")
  @NotBlank(message = "The field [name] cannot be blank.")
  @Size(min = 3, max = 50, message = "The [name] field must contain between 3 and 50 characters.")
  private String name;

  @JsonProperty(value = "brand")
  @NotNull(message = "The field [brand] cannot be null.")
  @NotBlank(message = "The field [brand] cannot be blank.")
  @Size(min = 3, max = 50, message = "The [brand] field must contain between 3 and 50 characters.")
  private String brand;

  @JsonProperty(value = "size")
  @NotBlank(message = "The field [size] cannot be blank.")
  private String size;

  @JsonProperty(value = "price")
  @NotNull(message = "The field [price] cannot be null.")
  @Min(value = 1, message = "The field [price] must have a minimum value of 1.00")
  @Max(value = 99999999, message = "The field [price] must have a maximum value of 99999999.00")
  private Double price;

  @JsonProperty(value = "principalImage")
  @NotNull(message = "The field [principalImage] cannot be null.")
  @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)", 
  message = "The [principalImage] field has to be a valid url.")
  private String principalImage;

  @JsonProperty(value = "otherImages")
  private List<@Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)", 
      message = "The [otherImages] field has to be a valid url.") String> otherImages;
}
