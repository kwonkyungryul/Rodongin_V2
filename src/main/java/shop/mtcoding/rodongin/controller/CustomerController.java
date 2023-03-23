
package shop.mtcoding.rodongin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;

import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.service.customer.CustomerService;

@RequiredArgsConstructor
@RestController
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("customers/{id}")
  public ResponseEntity<?> detail(@PathVariable Integer id) {
    CustomerDetailOutDto customerDetailOutDto = customerService.고객센터게시글상세보기(id);
    return new ResponseEntity<>(new ResponseDto<>(1, "게시글보기페이지 성공", customerDetailOutDto), HttpStatus.OK);
  }
}