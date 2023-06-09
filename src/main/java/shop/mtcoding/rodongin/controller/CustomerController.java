
package shop.mtcoding.rodongin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "7. 고객센터 정보관리", description = "고객센터 정보관리")
public class CustomerController {

  private final HttpSession session;

  private final CustomerService customerService;

  @PutMapping("/s/customers/{id}")
  @Operation(summary = "4. 게시글 수정", description = "게시글을 수정합니다.")
  public ResponseEntity<?> update(@PathVariable int id,
      @RequestBody CustomerUpdateInDto customerUpdateInDto) {

    if (customerUpdateInDto.getCustomerTitle() == null ||
        customerUpdateInDto.getCustomerTitle().isEmpty()) {
      throw new CustomException("title을 작성해주세요");
    }
    if (customerUpdateInDto.getCustomerContent() == null ||
        customerUpdateInDto.getCustomerContent().isEmpty()) {
      throw new CustomException("content를 작성해주세요");
    }
    customerService.글수정(id, customerUpdateInDto);
    return new ResponseEntity<>(new ResponseDto<>(1, "수정성공", null), HttpStatus.OK);
  }

  @DeleteMapping("/s/customers/{id}")
  @Operation(summary = "5. 게시글 삭제", description = "게시글을 삭제합니다.")
  public ResponseEntity<?> delete(@PathVariable int id) {
    customerService.게시글삭제(id);
    return new ResponseEntity<>(new ResponseDto<>(1, "삭제성공", null), HttpStatus.OK);
  }

  @PostMapping("/s/customers")
  @Operation(summary = "3. 게시글 추가", description = "게시글을 작성합니다.")
  public ResponseEntity<?> save(@RequestBody CustomerSaveInDto customerSaveInDto) {

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
    customerService.글쓰기(customerSaveInDto);
    return new ResponseEntity<>(new ResponseDto<>(1, "게시글 등록 성공", null), HttpStatus.CREATED);
  }

  @GetMapping("/customers/{id}")
  @Operation(summary = "2. 게시글 상세보기", description = "게시글 정보를 조회합니다.")
  public ResponseEntity<?> detail(@PathVariable int id) {
    CustomerDetailOutDto customerDetailOutDto = customerService.고객센터게시글상세보기(id);
    return new ResponseEntity<>(new ResponseDto<>(1, "게시글보기페이지 성공", customerDetailOutDto), HttpStatus.OK);
  }

  @GetMapping("/customers")
  @Operation(summary = "1. 게시글 리스트", description = "게시글 리스트를 조회합니다.")
  public ResponseEntity<?> list() {
    List<CustomerListOutDto> customerListOutDto = customerService.고객센터게시글리스트보기();

    return new ResponseEntity<>(new ResponseDto<>(1, "고객센터게시글리스트 보기성공", customerListOutDto), HttpStatus.OK);
  }

}