package conecta.vagas.api.domain.tag;

public record TagSelectData(Long id, String title) {
    public TagSelectData(Tag tag){
        this(tag.getID(), tag.getTitle());
    }
}
