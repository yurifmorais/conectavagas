package conecta.vagas.api.domain.jobVacancy;

public record JobVListingData(Long ID, String title, String description, String location, String filters, Double salary, String postDate, String endDate, String internshipType, String requirements, String benefits, Long user) {
    public JobVListingData(JobV jobV) {
        this(
                jobV.getID(),
                jobV.getTitle(),
                jobV.getDescription(),
                jobV.getLocation(),
                jobV.getFilters(),
                jobV.getSalary(),
                jobV.getPostDate().toString(),
                jobV.getEndDate().toString(),
                jobV.getInternshipType(),
                jobV.getRequirements(),
                jobV.getBenefits(),
                jobV.getUser().getID()
        );
    }
}