
package shop.mtcoding.rodongin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.customer.CustomerListOutDto;
import shop.mtcoding.rodongin.service.customer.CustomerService;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping({ "/customer/list", "customer" })
    public ResponseEntity<?> list() { // customer - main 리스트페이지
        CustomerListOutDto listDto = customerService.고객센터리스트();

        return new ResponseEntity<>(new ResponseDto<>(1, "고객센터 리스트 보기 성공", listDto), HttpStatus.OK);
    }

}