package conecta.vagas.api.controller;

import conecta.vagas.api.domain.jobVacancy.JobV;
import conecta.vagas.api.domain.jobVacancy.JobVDataRegister;
import conecta.vagas.api.domain.jobVacancy.JobVListingData;
import conecta.vagas.api.domain.jobVacancy.JobVRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vacancies")
//@SecurityRequirement(name = "bearer-key")
//tenho que ver a os ROLE do adm dps
public class JobVController {

    @Autowired
    JobVRepository jobVRepository;

    @GetMapping
    public ResponseEntity<Page<JobVListingData>> getJobV(Pageable paginacao) {
        var page = jobVRepository.findAll(paginacao).map(JobVListingData::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity addJobV(@RequestBody @Valid JobVDataRegister data, UriComponentsBuilder uriBuilder) {
        var jobV = new JobV(data);
        jobVRepository.save(jobV); //insert no banco
        var uri = uriBuilder.path("/jobvacancy/{ID}").buildAndExpand(jobV.getID()).toUri();
        return ResponseEntity.created(uri).body(new JobVListingData(jobV));
    }
    //generate a method to update a JOBvacancy
    //generate a method to delete a JobVacancy
}