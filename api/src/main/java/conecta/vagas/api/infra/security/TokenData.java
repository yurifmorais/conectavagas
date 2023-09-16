package conecta.vagas.api.infra.security;

//para devolver o token como um JSON
public record TokenData(Long id, String email, String name, Boolean is_company, String token) {
}
