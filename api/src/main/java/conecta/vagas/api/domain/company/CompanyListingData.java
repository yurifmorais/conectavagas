package conecta.vagas.api.domain.company;

public record CompanyListingData(
        Long ID,
        String cnpj,
        String nomeFantasia,
        Integer situacaoCadastral,
        String cnae,
        String uf,
        Integer municipio) {
    public CompanyListingData(Company company) {
        this(
                company.getID(),
                company.getCnpj(),
                company.getNomeFantasia(),
                company.getSituacaoCadastral(),
                company.getCnae(),
                company.getUf(),
                company.getMunicipio()
        );
    }
}
