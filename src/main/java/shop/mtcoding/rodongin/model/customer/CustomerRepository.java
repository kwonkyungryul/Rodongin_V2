package shop.mtcoding.rodongin.model.customer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.customer.CustomerDetailOutDto;
import shop.mtcoding.rodongin.dto.customer.CustomerListOutDto;

@Mapper
public interface CustomerRepository {

        public CustomerDetailOutDto findCustomerDetail(int id);

        public List<CustomerListOutDto> findCustomerList();

        // public CustomerDetailRespDto findByIdWithEmployee(int id);

        public List<Customer> findAll();

        public Customer findById(int id);

        public int insert(@Param("customerTitle") String customerTitle,
                        @Param("customerContent") String customerContent,
                        @Param("employeeId") Integer employeeId);

        public int updateById(@Param("id") Integer id, @Param("customerTitle") String customerTitle,
                        @Param("customerContent") String customerContent);

        public int deleteById(int id);
}
