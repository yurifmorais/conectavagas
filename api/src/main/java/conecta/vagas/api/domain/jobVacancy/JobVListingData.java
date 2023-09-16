package conecta.vagas.api.domain.jobVacancy;

public record JobVListingData(Long ID, String title, String description, String location, String filters, Double salary) {
    public JobVListingData(JobV jobV) {
        this(
                jobV.getID(),
                jobV.getTitle(),
                jobV.getDescription(),
                jobV.getLocation(),
                jobV.getFilters().toString(),
                jobV.getSalary()
        );
    }
}