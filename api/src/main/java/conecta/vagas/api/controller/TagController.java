package conecta.vagas.api.controller;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.jobVacancy.JobVDTO;
import conecta.vagas.api.domain.jobVacancy.JobVRepository;
import conecta.vagas.api.domain.tag.Tag;
import conecta.vagas.api.domain.tag.TagRepository;
import conecta.vagas.api.domain.tag.TagSelectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private JobVRepository jobVRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/select")
    public ResponseEntity getTagsForSelect(){
        var tags = tagRepository.findAll().stream().map(TagSelectData::new);

        return ResponseEntity.ok(tags);
    }

    //verificar abaixo
    //vou fazer um metodo que vai receber uma tag e vai retornar as vagas que tem essa tag
    @GetMapping("/{tagTitle}")
    public ResponseEntity getJobVacanciesByTag(@PathVariable String tagTitle){
        var tags = tagRepository.findByTitle(tagTitle);

        if (tags.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var jobVacancies = new ArrayList<>();
        for (Tag tag : tags) {
            var jobs = jobVRepository.findByTagsContains(tag);
            for (Object job : jobs) {
                jobVacancies.add(new JobVDTO((JobV) job));
            }
        }
        return ResponseEntity.ok(jobVacancies);
    }
}