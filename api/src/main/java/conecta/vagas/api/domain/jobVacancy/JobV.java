package conecta.vagas.api.domain.jobVacancy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")//pk

@Entity
@Table(name = "job_vacancies")
public class JobV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    private Filters filters;

    @Column(name = "salary")
    private Double salary;

    public JobV(JobVDataRegister jobVDataRegister) {
        this.title = jobVDataRegister.title();
        this.description = jobVDataRegister.description();
        this.location = jobVDataRegister.location();
        this.filters = jobVDataRegister.filters();
        this.salary = jobVDataRegister.salary();
    }

    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getLocation() {
        return location;
    }

}