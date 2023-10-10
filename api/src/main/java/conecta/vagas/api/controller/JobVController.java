package conecta.vagas.api.controller;
import org.springframework.data.domain.Range;
import org.springframework.security.core.Authentication;

import conecta.vagas.api.domain.jobVacancy.*;
import conecta.vagas.api.domain.user.User;
import conecta.vagas.api.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/myvacancies")
@SecurityRequirement(name = "bearer-key")
public class JobVController {

    @Autowired
    JobVRepository jobVRepository;
    @Autowired
    UserRepository userRepository;

    //teste acima
    @GetMapping
    public ResponseEntity<Page<JobVListingData>> getJobV(Pageable paginacao) {
        // Obtenha o usuário atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = (User) userRepository.findByEmail(currentPrincipalName);

        // Verifica se o usuário atual é do tipo 'company'
        if (currentUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"))) {
            // Se for uma empresa, mostre apenas as vagas de emprego que ela cadastrou
            var page = jobVRepository.findByUser(currentUser, paginacao).map(JobVListingData::new);
            return ResponseEntity.ok(page);
        } else {
            // Se for um usuário, ele pode ver todas as vagas de emprego
            var page = jobVRepository.findAll(paginacao).map(JobVListingData::new);
            return ResponseEntity.ok(page);
        }
    }

    //teste abaixo

//    @GetMapping
//    public ResponseEntity<Page<JobVListingData>> getJobV(Pageable paginacao) {
//        var page = jobVRepository.findAll(paginacao).map(JobVListingData::new);
//        return ResponseEntity.ok(page);
//    }

    @GetMapping("/{id}")
    public ResponseEntity datail(@PathVariable Long id) {
        var jobV = jobVRepository.getReferenceById(id);
        return ResponseEntity.ok(new JobVListingData(jobV));
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
        Optional<JobV> jobVOptional = jobVRepository.findById(dto.id());
        if (jobVOptional.isPresent()) {
            JobV jobV = jobVOptional.get();
            // Obtenha o usuário atual
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User currentUser = (User) userRepository.findByEmail(currentPrincipalName);

            // Verifica se o usuário atual é o proprietário da vaga
            if (jobV.getUser().equals(currentUser)) {
                jobV.updateData(dto);
                return ResponseEntity.ok(new JobVListingData(jobV));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // posso tratar melhor esse erro
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteJobV(@PathVariable Long id) {
        Optional<JobV> jobVOptional = jobVRepository.findById(id);
        if (jobVOptional.isPresent()) {
            JobV jobV = jobVOptional.get();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User currentUser = (User) userRepository.findByEmail(currentPrincipalName);

            // Verifica se o usuário atual é o proprietário da vaga
            if (jobV.getUser().equals(currentUser)) {
                jobVRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // posso tratar melhor esse erro
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}