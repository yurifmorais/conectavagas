package conecta.vagas.api.controller;
import conecta.vagas.api.domain.jobVacancy.JobVListingData;
import conecta.vagas.api.domain.person.Person;
import conecta.vagas.api.domain.person.PersonDataRegister;
import conecta.vagas.api.domain.person.PersonRepository;
import conecta.vagas.api.domain.tag.TagRepository;
import conecta.vagas.api.domain.vacancyRecommendation.VacancyRecommendationRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    VacancyRecommendationRepository recommendationRepository;

    @PostMapping("/registerPerson")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PersonDataRegister data) {
        var user = new Person(data);
        var encodedPassword = passwordEncoder().encode(data.password);

        user.setPassword(encodedPassword);

        if(!data.tagIds.isEmpty())
            user.setTags(new HashSet<>(tagRepository.findAllById(data.tagIds)));

        personRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @GetMapping("/person/{id}/recommendations")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity getRecommendations(@PathVariable long id, Pageable pagination){
        var user = personRepository.findById(id);

        if(user.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");

        var recommendations = recommendationRepository.findByUser(user.get(), pagination).map(vacancy -> new JobVListingData(vacancy.getJobVacancy()));

        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/person/{id}/contact")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity getPersonContact(@PathVariable long id){
        var person = personRepository.findById(id);

        if(person.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");

        var contact = new HashMap<String, String>();
        contact.put("linkedin", person.get().getLinkedin());
        contact.put("github", person.get().getGithub());

        return ResponseEntity.ok(contact);
    }
}