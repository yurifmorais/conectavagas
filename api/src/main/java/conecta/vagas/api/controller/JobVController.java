package conecta.vagas.api.controller;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.jobVacancy.JobVDataRegister;
import conecta.vagas.api.domain.jobVacancy.JobVListingData;
import conecta.vagas.api.domain.jobVacancy.JobVRepository;
import conecta.vagas.api.domain.user.User;
import conecta.vagas.api.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/vacancies")
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasRole('ROLE_COMPANY')")
public class JobVController {

    @Autowired
    JobVRepository jobVRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Page<JobVListingData>> getJobV(Pageable paginacao) {
        var page = jobVRepository.findAll(paginacao).map(JobVListingData::new);
        System.out.println("TO GEEEEEEEEEEEEEET");
        return ResponseEntity.ok(page);
    }

//    @PostMapping
//    @Transactional
//    public ResponseEntity addJobV(@RequestBody @Valid JobVDataRegister data, UriComponentsBuilder uriBuilder) {
//        var jobV = new JobV(data);
//        System.out.println("TO Aqqqqqqqqqqqqqqqqqqqqqqqqqqqqui");
//        System.out.println(data.user());
//        jobVRepository.save(jobV); //insert no banco
//        var uri = uriBuilder.path("/jobvacancy/{ID}").buildAndExpand(jobV.getID()).toUri();
//        return ResponseEntity.created(uri).body(new JobVListingData(jobV));
//    }
    //generate a method to update a JOBvacancy
    //generate a method to delete a JobVacancy

    @PostMapping
    @Transactional
    public ResponseEntity addJobV(@RequestBody @Valid JobVDataRegister dto, UriComponentsBuilder uriBuilder) {
        Optional<User> userOptional = userRepository.findById(dto.userId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            var jobV = new JobV(dto, user);
            jobVRepository.save(jobV);

            var uri = uriBuilder.path("/jobvacancy/{ID}").buildAndExpand(jobV.getID()).toUri();
            return ResponseEntity.created(uri).body(new JobVListingData(jobV));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}