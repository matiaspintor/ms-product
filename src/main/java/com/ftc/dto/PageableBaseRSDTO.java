package com.ftc.dto;

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
public class PageableBaseRSDTO {
  Long totalItems;
  Integer totalPages;
  Integer currentPage;
}
