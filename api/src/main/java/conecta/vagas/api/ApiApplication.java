package conecta.vagas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
	//a unica coisa que nao esta funcionando e a parte de get applications, do usuario logado
	//a empresa so pode ver as vagas que ela mesma cadastrou!
	//preciso deixar o email como unico
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}