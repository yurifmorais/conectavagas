package conecta.vagas.api.controller;

import conecta.vagas.api.domain.company.CompanyListingData;
import conecta.vagas.api.domain.company.CompanyRepository;
import conecta.vagas.api.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
//@SecurityRequirement(name = "bearer-key")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    //teste abaixo
    @GetMapping
    public ResponseEntity<Page<CompanyListingData>> getCompanies(Pageable paginacao) {
        var page = companyRepository.findAll(paginacao).map(CompanyListingData::new);
        return ResponseEntity.ok(page);
    }
}

