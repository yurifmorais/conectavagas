package conecta.vagas.api.controller;

import conecta.vagas.api.domain.company.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/companies")
//@SecurityRequirement(name = "bearer-key")
//tenho que ver a os ROLE do adm dps
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<Page<CompanyListingData>> getCompanies(Pageable paginacao) {
        var page = companyRepository.findAll(paginacao).map(CompanyListingData::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity createCompany(@RequestBody @Valid CompanyDataRegister data, UriComponentsBuilder uriBuilder) {
        var company = new Company(data);
        companyRepository.save(company); //insert no banco
        var uri = uriBuilder.path("/company/{ID}").buildAndExpand(company.getID()).toUri();//testar minusculo se nao funcionar
        return ResponseEntity.created(uri).body(new CompanyDetailsData(company));
    }
    //generate a method to update a company
    //generate a method to delete a company
}