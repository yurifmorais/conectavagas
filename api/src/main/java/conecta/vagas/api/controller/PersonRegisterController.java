package conecta.vagas.api.controller;
import conecta.vagas.api.domain.person.Person;
import conecta.vagas.api.domain.person.PersonDataRegister;
import conecta.vagas.api.domain.tag.TagRepository;
import conecta.vagas.api.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/registerPerson")
public class PersonRegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PersonDataRegister data) {
        var user = new Person(data);
        var encodedPassword = passwordEncoder().encode(data.password);

        user.setPassword(encodedPassword);

        if(!data.tagIds.isEmpty())
            user.setTags(new HashSet<>(tagRepository.findAllById(data.tagIds)));

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}