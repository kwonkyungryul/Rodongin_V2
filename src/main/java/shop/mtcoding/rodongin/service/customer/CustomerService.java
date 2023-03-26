package shop.mtcoding.rodongin.service.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerListOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerSaveInDto;
import shop.mtcoding.rodongin.dto.customer.CustomerUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.customer.Customer;
import shop.mtcoding.rodongin.model.customer.CustomerRepository;
import shop.mtcoding.rodongin.model.employee.Employee;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  private final HttpSession session;


  @Transactional
  public CustomerDetailOutDto 고객센터게시글상세보기(int id) {

    CustomerDetailOutDto customerDetailOutDto = customerRepository.findCustomerDetail(id);
    return customerDetailOutDto;
  }

  @Transactional
  public void 글쓰기(CustomerSaveInDto customerSaveInDto) {
    Employee principal = (Employee) session.getAttribute("principal");
    try {
      customerRepository.insert(principal.getId(), customerSaveInDto);
    } catch (Exception e) {
      throw new CustomApiException("게시글 등록 실패!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional
  public List<CustomerListOutDto> 고객센터게시글리스트보기() {

    List<CustomerListOutDto> customerListOutDto = customerRepository.findCustomerList();
    return customerListOutDto;

  }

  @Transactional
  public void 게시글삭제(Integer id) {
    Employee principal = (Employee) session.getAttribute("principal");

    Customer customerPS = customerRepository.findById(id);
    if (customerPS == null) {
      throw new CustomApiException("없는 게시글을 삭제할 수 없습니다");
    }
    if (customerPS.getEmployeeId().intValue() != principal.getId()) { // 만약에 오류나면 이쪽..
      throw new CustomApiException("해당 게시글을 삭제할 권한이 없습니다", HttpStatus.FORBIDDEN);
    }

    try {
      customerRepository.deleteById(id);
    } catch (Exception e) {
      throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
      // 로그를 남겨야 함 (DB or File)
    }
  }

  public void 글수정(Integer id, CustomerUpdateInDto customerUpdateInDto) {
    Employee principal = (Employee) session.getAttribute("principal");

    Customer customerPS = customerRepository.findById(id);
    if (customerPS == null) {
      throw new CustomApiException("글이 존재 하지않아 수정이 불가합니다.");
    }
    if (customerPS.getEmployeeId() != principal.getId()) {
      throw new CustomApiException("게시물의 수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
    }
    try {
      customerRepository.updateById(id, customerUpdateInDto);
    } catch (Exception e) {
      throw new CustomApiException("서버의 문제로 글수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
