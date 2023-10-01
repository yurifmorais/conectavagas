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
@RequestMapping("/vacancies") //mudei aqui!
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasRole('ROLE_USER')")
public class PublicJobVController {

    @Autowired
    JobVRepository jobVRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Page<JobVListingData>> getJobV(Pageable paginacao) {
        var page = jobVRepository.findAll(paginacao).map(JobVListingData::new);
        return ResponseEntity.ok(page);
    }

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