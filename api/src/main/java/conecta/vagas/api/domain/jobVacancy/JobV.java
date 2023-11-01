package conecta.vagas.api.domain.jobVacancy;

import conecta.vagas.api.domain.Application.Application;
import conecta.vagas.api.domain.tag.Tag;
import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")
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

    @Column(name = "salary")
    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "internship_type")
    private String internshipType;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "benefits")
    private String benefits;

//    @OneToMany(mappedBy = "jobV")
//    private Set<Application> applications;

    @OneToMany(mappedBy = "jobV", cascade = CascadeType.ALL)
    private Set<Application> applications;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "job_vacancies_tags",
            joinColumns = @JoinColumn(name = "job_vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public JobV(JobVDataRegister dto, User user) {
        this.title = dto.title();
        this.description = dto.description();
        this.location = dto.location();
        this.salary = dto.salary();
        this.postDate = dto.postDate();
        this.endDate = dto.endDate();
        this.internshipType = dto.internshipType();
        this.requirements = dto.requirements();
        this.benefits = dto.benefits();
        this.user = user;
//        if (user != null) {
//            user.getJobVs().add(this);
//        }
    }

    public void updateData(JobVDataUpdate dto) {
        if(dto.id() != null) {
            this.ID = dto.id();
        }
        if(dto.title() != null) {
            this.title = dto.title();
        }
        if(dto.description() != null) {
            this.description = dto.description();
        }
        if (dto.location() != null){
            this.location = dto.location();
        }
        if(dto.salary() != null) {
            this.salary = dto.salary();
        }
    }
}