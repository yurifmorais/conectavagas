package conecta.vagas.api.domain.user;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public class UserDataRegister {
        public String name;
        //i want to make the email unique
        @Email
        public String email; //tenho que deixar o email unico
        public String password;
        public boolean user_type;

        public UserDataRegister() {
        }
        public String getName() {
                return name;
        }
        public String getEmail() {
                return email;
        }
        public String getPassword() {
                return password;
        }
        public Boolean getUser_type() {
                return user_type;
        }
}