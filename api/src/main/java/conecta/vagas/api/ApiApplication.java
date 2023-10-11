package conecta.vagas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
	//preciso tratar o erro do email duplicado -> adicionar unique no script do email
	//preciso dar uma limpada nas entidades, tirar os getters and setters desnecessarios
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}