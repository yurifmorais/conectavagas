package conecta.vagas.api.domain.tag;

import conecta.vagas.api.domain.jobVacancy.JobV;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Tag parent;

//    @ManyToMany(mappedBy = "tags")
//    private Set<JobV> jobVacancies;

    public Tag(Long id){
        ID = id;
    }
}
