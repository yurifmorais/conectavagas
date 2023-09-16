package conecta.vagas.api.domain.company;

public record CompanyDetailsData(
        Long ID,
        String cnpj,
        String nomeFantasia,
        Integer situacaoCadastral,
        String cnae,
        String uf,
        Integer municipio) {
    public CompanyDetailsData(Company company) {
        this(company.getID(), company.getCnpj(), company.getNomeFantasia(), company.getSituacaoCadastral(), company.getCnae(), company.getUf(), company.getMunicipio());
    }
}