package conecta.vagas.api.controller;

import conecta.vagas.api.domain.jobVacancy.*;
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
@RequestMapping("/myvacancies") //mudei aqui!
@SecurityRequirement(name = "bearer-key")
//@PreAuthorize("hasRole('ROLE_COMPANY')")
public class JobVController {

    @Autowired
    JobVRepository jobVRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Page<JobVListingData>> getJobV(Pageable paginacao) {
        var page = jobVRepository.findAll(paginacao).map(JobVListingData::new);
        return ResponseEntity.ok(page);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY')")
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
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @PutMapping
    @Transactional
    public ResponseEntity updateJobV(@RequestBody @Valid JobVDataUpdate dto, UriComponentsBuilder uriBuilder) {
        var jobV = jobVRepository.getReferenceById(dto.id());
        jobV.updateData(dto);
        return ResponseEntity.ok(new JobVListingData(jobV));
    }

    @GetMapping("/{id}")
    public ResponseEntity datail(@PathVariable Long id) {
        var jobV = jobVRepository.getReferenceById(id);
        return ResponseEntity.ok(new JobVListingData(jobV));
    }

    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteJobV(@PathVariable Long id) {
        Optional<JobV> jobVOptional = jobVRepository.findById(id);
        if (jobVOptional.isPresent()) {
            jobVRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}