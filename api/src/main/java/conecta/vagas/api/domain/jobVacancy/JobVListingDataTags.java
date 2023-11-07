package conecta.vagas.api.domain.jobVacancy;

import conecta.vagas.api.domain.tag.Tag;

public record JobVListingDataTags(Long id, String title) {
    public JobVListingDataTags(Tag tag){
        this(tag.getID(), tag.getTitle());
    }
}
