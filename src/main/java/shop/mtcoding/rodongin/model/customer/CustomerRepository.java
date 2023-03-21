package shop.mtcoding.rodongin.model.customer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.customer.CustomerResp.CustomerDetailRespDto;
import shop.mtcoding.rodongin.dto.customer.CustomerResp.CustomerListRespDto;

@Mapper
public interface CustomerRepository {

        public CustomerDetailRespDto findCustomerDetail(int id);

        public List<CustomerListRespDto> findCustomerList();

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
