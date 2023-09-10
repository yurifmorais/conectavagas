package conecta.vagas.api.domain.company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")//pk

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "SITUACAO_CADASTRAL")
    private Integer situacaoCadastral;

    @Column(name = "CNAE")
    private String cnae;

    @Column(name = "UF")
    private String uf;

    @Column(name = "Municipio")
    private Integer municipio;

    public Company(CompanyDataRegister companyDataRegister) {
        this.cnpj = companyDataRegister.cnpj();
        this.nomeFantasia = companyDataRegister.nomeFantasia();
        this.situacaoCadastral = companyDataRegister.situacaoCadastral();
        this.cnae = companyDataRegister.cnae();
        this.uf = companyDataRegister.uf();
        this.municipio = companyDataRegister.municipio();
    }

    public Long getID() {
        return ID;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String newCnpj) {
        this.cnpj = newCnpj;
    }
    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String newNomeFantasia) {
        this.nomeFantasia = newNomeFantasia;
    }
    public Integer getSituacaoCadastral() {
        return situacaoCadastral;
    }
    public void setSituacaoCadastral(Integer newSituacaoCadastral) {
        this.situacaoCadastral = newSituacaoCadastral;
    }
    public String getCnae() {
        return cnae;
    }
    public void setCnae(String newCnae) {
        this.cnae = newCnae;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String newUf) {
        this.uf = newUf;
    }
    public Integer getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Integer newMunicipio) {
        this.municipio = newMunicipio;
    }
}

