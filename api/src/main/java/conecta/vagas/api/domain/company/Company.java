package conecta.vagas.api.domain.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import conecta.vagas.api.domain.jobVacancy.JobV;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("company")
public class Company extends User {

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "city")
    private String city;

    //teste
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobV> jobVs = new HashSet<>();

    public Company(CompanyDataRegister companyDataRegister) {
        super(companyDataRegister);
        this.cnpj = companyDataRegister.getCnpj();
        this.city = companyDataRegister.getCity();
        //teste
        this.jobVs = new HashSet<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String newCnpj) {
        this.cnpj = newCnpj;
    }

    // Adicionado setter para jobVs - teste
    public void setJobVs(Set<JobV> jobVs) {
        this.jobVs.clear();
        if (jobVs != null) {
            this.jobVs.addAll(jobVs);
        }
    }
}