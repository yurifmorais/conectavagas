package conecta.vagas.api.domain.user;

import conecta.vagas.api.domain.company.Company;
import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@EqualsAndHashCode(of = "ID")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private String email;
    private String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<JobV> jobVs = new HashSet<>();

    public User(UserDataRegister userDataRegister) {
        this.name = userDataRegister.getName();
        this.email = userDataRegister.getEmail();
        this.password = userDataRegister.getPassword();
        //this.jobVs = new HashSet<>();
    }

    //true se e empresa, false se e pessoa
    public Boolean getUserType() {
        if (this instanceof Person) {
            return false;
        } else if (this instanceof Company) {
            return true;
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (getUserType()) {
            return List.of(new SimpleGrantedAuthority("ROLE_COMPANY"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    // Adicionado setter para jobVs

//    public void setJobVs(Set<JobV> jobVs) {
//        this.jobVs.clear();
//        if (jobVs != null) {
//            this.jobVs.addAll(jobVs);
//        }
//    }
}
