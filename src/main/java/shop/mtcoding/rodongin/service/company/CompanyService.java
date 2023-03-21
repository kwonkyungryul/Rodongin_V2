package shop.mtcoding.rodongin.service.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.company.CompanyDetailOutDto;
import shop.mtcoding.rodongin.model.company.CompanyRepository;

@RequiredArgsConstructor
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyDetailOutDto 기업상세보기(int id) {
        CompanyDetailOutDto DetailDto = companyRepository.findById(id);
        return DetailDto;
    }
}
