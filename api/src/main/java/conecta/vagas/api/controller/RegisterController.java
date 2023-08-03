package conecta.vagas.api.controller;

import conecta.vagas.api.domain.user.User;
import conecta.vagas.api.domain.user.UserDataRegister;
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

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserDataRegister data) {
        var user = new User(data);
        var encodedPassword = passwordEncoder().encode(data.password());

        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}