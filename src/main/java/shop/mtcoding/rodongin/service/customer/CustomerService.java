package shop.mtcoding.rodongin.service.customer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.customer.CustomerRepository;

@RequiredArgsConstructor
@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  @Transactional
  public CustomerDetailOutDto 고객센터게시글상세보기(int id) {

    CustomerDetailOutDto customerDetailOutDto = customerRepository.findCustomerDetail(id);
    return customerDetailOutDto;
  }

  @Transactional
  public void 글쓰기(int principalId, CustomerSaveInDto customerSaveInDto) {
    customerRepository.insert(principalId, customerSaveInDto);
    try {
    } catch (Exception e) {
      throw new CustomApiException("게시글 등록 실패!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
