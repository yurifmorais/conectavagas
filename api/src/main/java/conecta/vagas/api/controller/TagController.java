package conecta.vagas.api.controller;

import conecta.vagas.api.domain.tag.TagRepository;
import conecta.vagas.api.domain.tag.TagSelectData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagController {
    private TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/select")
    public ResponseEntity getTagsForSelect(){
        var tags = tagRepository.findAll().stream().map(TagSelectData::new);

        return ResponseEntity.ok(tags);
    }
}
