package shop.mtcoding.rodongin.service.customer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerListOutDto;
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
  public List<CustomerListOutDto> 고객센터게시글리스트보기() {

    List<CustomerListOutDto> customerListOutDto = customerRepository.findCustomerList();
    return customerListOutDto;
  }
}
