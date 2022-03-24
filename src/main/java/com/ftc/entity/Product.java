package com.ftc.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter()
@Setter()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Product {

  @Id
  @Column(name = "sku", nullable = false)
  private String sku;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "brand", nullable = false, length = 50)
  private String brand;

  @Column(name = "size", nullable = true)
  private String size;

  @Column(name = "price", nullable = false, precision = 2)
  private Double price;

  @Column(name = "principal_image", nullable = false)
  private String principalImage;

  @Column(name = "other_image", nullable = true)
  @ElementCollection()
  private List<String> otherImages;
}
