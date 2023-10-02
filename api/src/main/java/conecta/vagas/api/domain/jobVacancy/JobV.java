package conecta.vagas.api.domain.jobVacancy;

import conecta.vagas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")
@Entity
@Table(name = "JOB_VACANCIES")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "filters")
    private Filters filters;

//    public JobV(JobVDataRegister jobVDataRegister) {
//        this.title = jobVDataRegister.title();
//        this.description = jobVDataRegister.description();
//        this.location = jobVDataRegister.location();
//        this.filters = jobVDataRegister.filters();
//        this.salary = jobVDataRegister.salary();
//        this.postDate = jobVDataRegister.postDate();
//        this.endDate = jobVDataRegister.endDate();
//        this.internshipType = jobVDataRegister.internshipType();
//        this.requirements = jobVDataRegister.requirements();
//        this.benefits = jobVDataRegister.benefits();
//        this.user = jobVDataRegister.user(); // Adicionado
//    }

    public JobV(JobVDataRegister dto, User user) {
        this.title = dto.title();
        this.description = dto.description();
        this.location = dto.location();
        this.filters = dto.filters();
        this.salary = dto.salary();
        this.postDate = dto.postDate();
        this.endDate = dto.endDate();
        this.internshipType = dto.internshipType();
        this.requirements = dto.requirements();
        this.benefits = dto.benefits();
        this.user = user;
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

    //generate the getter for the filters. i want to return a string
    public String getFilters() {
        return filters.toString();
    }

    // Adicionado setters para os outros campos

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setInternshipType(String internshipType) {
        this.internshipType = internshipType;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            user.getJobVs().add(this);
        }
    }
}
