package conecta.vagas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	//verificar as novas rotas. added linkedin and github to the user person
	// prox passo: adicionar um filtro por tags na rota de buscar as vaga
}