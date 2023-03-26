
package shop.mtcoding.rodongin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerListOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerSaveInDto;
import shop.mtcoding.rodongin.dto.customer.CustomerUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.service.customer.CustomerService;
import shop.mtcoding.rodongin.util.MySession;

@RequiredArgsConstructor
@RestController
public class CustomerController {

  private final HttpSession session;

  private final CustomerService customerService;

  @PutMapping("/customers/{id}")
  public ResponseEntity<?> update(@PathVariable int id,
      @RequestBody CustomerUpdateInDto customerUpdateInDto) {

    // Employee principal = (Employee) session.getAttribute("principal");
    Employee principal = MySession.MyPrincipal(session);

    if (principal == null) {
      throw new CustomException("인증이 되지 않습니다", HttpStatus.UNAUTHORIZED);
    }

    if (customerUpdateInDto.getCustomerTitle() == null ||
        customerUpdateInDto.getCustomerTitle().isEmpty()) {
      throw new CustomException("title을 작성해주세요");
    }
    if (customerUpdateInDto.getCustomerContent() == null ||
        customerUpdateInDto.getCustomerContent().isEmpty()) {
      throw new CustomException("content를 작성해주세요");
    }
    customerService.글수정(principal.getId(), id, customerUpdateInDto);
    return new ResponseEntity<>(new ResponseDto<>(1, "수정성공", null), HttpStatus.OK);
  }

  @DeleteMapping("/customers/{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {

    // Employee principal = (Employee) session.getAttribute("principal");
    Employee principal = MySession.MyPrincipal(session);
    if (principal == null) {
      throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
    }
    customerService.게시글삭제(principal.getId(), id);
    return new ResponseEntity<>(new ResponseDto<>(1, "삭제성공", null), HttpStatus.OK);
  }

  @PostMapping("/customers")
  public ResponseEntity<?> save(@RequestBody CustomerSaveInDto customerSaveInDto) {

    // Employee principal = (Employee) session.getAttribute("principal");
    Employee principal = MySession.MyPrincipal(session);

    if (principal == null) {
      throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
    }

    if (customerSaveInDto.getCustomerTitle().length() > 100) {
      throw new CustomApiException("제목의 길이가 100자 이하여야 합니다.");
    }
    if (customerSaveInDto.getCustomerTitle().isEmpty()) {
      throw new CustomApiException("제목을 입력해 주세요.");
    }
    if (customerSaveInDto.getCustomerContent() == null ||
        customerSaveInDto.getCustomerContent().isEmpty()) {
      throw new CustomApiException("내용을 작성해 주세요.");
    }
    System.out.println("테스트 : " + principal.getId());
    customerService.글쓰기(principal.getId(), customerSaveInDto);
    return new ResponseEntity<>(new ResponseDto<>(1, "게시글 등록 성공", null), HttpStatus.CREATED);
  }

  @GetMapping("customers/{id}")
  public ResponseEntity<?> detail(@PathVariable int id) {
    CustomerDetailOutDto customerDetailOutDto = customerService.고객센터게시글상세보기(id);
    return new ResponseEntity<>(new ResponseDto<>(1, "게시글보기페이지 성공", customerDetailOutDto), HttpStatus.OK);
  }

  @GetMapping("/customers")
  public ResponseEntity<?> list() {
    List<CustomerListOutDto> customerListOutDto = customerService.고객센터게시글리스트보기();

    return new ResponseEntity<>(new ResponseDto<>(1, "고객센터게시글리스트 보기성공", customerListOutDto), HttpStatus.OK);
  }

}