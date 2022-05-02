package domain;

public class Autor {
    Long id;
    String nome;
    String nacionalidade;
    Integer anoNascimento;

    public Autor(){

    }
    public Autor(Long id, String nome, String nacionalidade, Integer anoNascimento){
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.anoNascimento = anoNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nome=" + nome + ", nacionalidade=" + nacionalidade + ", ano de nascimento="
                + anoNascimento;
    }
}
