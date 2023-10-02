package conecta.vagas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
//revisar a parte de adm e empresa. montar o cadastro de vaga correto. alem disso, se for empresa, vai ter alguns dados a mais que o user normal
	//montar a parte de editar vaga, excluir vaga.
	//montar a parte de se candidatar pra uma vaga. vou guardar todas as vagas q o usuario se candidatou
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}