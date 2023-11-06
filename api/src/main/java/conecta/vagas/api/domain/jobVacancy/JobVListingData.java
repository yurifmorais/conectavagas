package conecta.vagas.api.domain.jobVacancy;

import conecta.vagas.api.domain.tag.Tag;

import java.util.Set;

public record JobVListingData(Long ID, String title, String description, String location, Double salary, String postDate, String endDate, String internshipType, String requirements, String benefits, Long user, Set<Tag> tags) {
    public JobVListingData(JobV jobV) {
        this(
                jobV.getID(),
                jobV.getTitle(),
                jobV.getDescription(),
                jobV.getLocation(),
                jobV.getSalary(),
                jobV.getPostDate().toString(),
                jobV.getEndDate().toString(),
                jobV.getInternshipType(),
                jobV.getRequirements(),
                jobV.getBenefits(),
                jobV.getUser().getID(),
                jobV.getTags()
        );
    }

}