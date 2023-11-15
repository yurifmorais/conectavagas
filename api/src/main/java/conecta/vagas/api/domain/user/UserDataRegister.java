package conecta.vagas.api.domain.user;

import jakarta.validation.constraints.Email;

public class UserDataRegister {
        public String name;
        @Email
        public String email;
        public String password;
        public UserDataRegister() {
        }
}