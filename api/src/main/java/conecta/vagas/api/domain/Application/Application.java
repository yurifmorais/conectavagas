package conecta.vagas.api.domain.Application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    //@Column(name = "job_vacancy_id")
    @ManyToOne
    @JoinColumn(name = "job_vacancy_id")
    private JobV jobV;

    @Column(name = "application_date")
    private Date applicationDate;
}

