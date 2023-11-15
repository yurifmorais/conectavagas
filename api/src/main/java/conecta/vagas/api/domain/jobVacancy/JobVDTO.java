// JobVDTO.java
package conecta.vagas.api.domain.jobVacancy;

public record JobVDTO(
        String title,
        String description,
        String location,
        Double salary,
        String postDate
) {
    public JobVDTO(JobV jobV) {
        this(jobV.getTitle(), jobV.getDescription(), jobV.getLocation(), jobV.getSalary(), jobV.getPostDate().toString());
    }
}