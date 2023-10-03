package conecta.vagas.api.controller;

import java.util.Objects;

import conecta.vagas.api.domain.jobVacancy.JobVListingData;
import conecta.vagas.api.domain.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import conecta.vagas.api.domain.Application.Application;
import conecta.vagas.api.domain.Application.ApplicationData;
import conecta.vagas.api.domain.Application.ApplicationRepository;
import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.jobVacancy.JobVRepository;
import conecta.vagas.api.domain.person.Person;
import conecta.vagas.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/applications/{id}")
@PreAuthorize("hasRole('USER')")
public class ApplicationsController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobVRepository jobVRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> apply(@RequestBody ApplicationData applicationData) {
        return userRepository.findById(applicationData.personID())
                .map(user -> {
                    if (!(user instanceof Person)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Somente pessoas podem se candidatar a vagas");
                    }
                    Person person = (Person) user;
                    JobV jobV = jobVRepository.findById(applicationData.jobID())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));
                    Application application = new Application();
                    application.setPerson(person);
                    application.setJobV(jobV);
                    application.setApplicationDate(new Date());
                    applicationRepository.save(application);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @GetMapping
    public ResponseEntity<?> getApplications(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        String email = userDetails.getUsername();
        User user = (User) userRepository.findByEmail(email);

        if (user instanceof Person) {
            Person person = (Person) user;
            if (!Objects.equals(person.getID(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você só pode ver suas próprias candidaturas");
            }
            Set<Application> applications = applicationRepository.findByPersonId(id);
            Set<JobVListingData> jobVListings = applications.stream()
                    .map(Application::getJobV)
                    .map(JobVListingData::new) // convert JobV to JobVListingData
                    .collect(Collectors.toSet());
            System.out.println(jobVListings);
            return ResponseEntity.ok(jobVListings);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Somente pessoas podem ver suas candidaturas");
        }
    }

    @DeleteMapping("/{applicationId}")
    @Transactional
    public ResponseEntity<?> deleteApplication(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @PathVariable Long applicationId) {
        String email = userDetails.getUsername();
        User user = (User) userRepository.findByEmail(email);

        if (user instanceof Person) {
            Person person = (Person) user;
            if (!Objects.equals(person.getID(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você só pode excluir suas próprias candidaturas");
            }
            Application application = applicationRepository.findById(applicationId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada"));
            if (!Objects.equals(application.getPerson().getID(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você só pode excluir suas próprias candidaturas");
            }
            applicationRepository.delete(application);
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Somente pessoas podem excluir suas candidaturas");
        }
    }


}