package conecta.vagas.api.domain.user;
import jakarta.validation.constraints.Email;

public class UserDataRegister {
        public String name;
        @Email
        public String email;
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