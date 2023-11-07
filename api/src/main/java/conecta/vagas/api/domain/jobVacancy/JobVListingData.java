package conecta.vagas.api.domain.jobVacancy;

import java.util.List;

public record JobVListingData(Long ID, String title, String description, String location, Double salary, String postDate, String endDate, String internshipType, String requirements, String benefits, Long user, List<JobVListingDataTags> tags) {
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
                jobV.getTags().stream().map(JobVListingDataTags::new).toList()
        );
    }
}