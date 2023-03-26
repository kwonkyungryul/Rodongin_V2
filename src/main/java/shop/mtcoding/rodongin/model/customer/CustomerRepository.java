package shop.mtcoding.rodongin.model.customer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerListOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerSaveInDto;
import shop.mtcoding.rodongin.dto.customer.CustomerUpdateInDto;

@Mapper
public interface CustomerRepository {

        public CustomerDetailOutDto findCustomerDetail(int id);

        public List<CustomerListOutDto> findCustomerList();

        // public CustomerDetailRespDto findByIdWithEmployee(int id);

        public List<Customer> findAll();

        public Customer findById(int id);

        public int insert(@Param("employeeId") Integer employeeId,
                        @Param("customerSaveInDto") CustomerSaveInDto customerSaveInDto);

        public int updateById(@Param("id") Integer id,
                        @Param("customerUpdateInDto") CustomerUpdateInDto customerUpdateInDto);

        public int deleteById(int id);
}
