package domain;

import java.util.List;

public class Livro {
    private Long id;
    private String titulo;
    private String isbn;
    private Integer edicao;
    private String descricao;
    private List<Autor> autores;

    public Livro(){

    }

    public Livro(String titulo, String isbn, Integer edicao, String descricao, List<Autor> autores) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicao = edicao;
        this.descricao = descricao;
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(long l) {
        this.id = l;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Livro [autores=" + autores + ", descricao=" + descricao + ", edicao=" + edicao + ", id=" + id
                + ", isbn=" + isbn + ", titulo=" + titulo + "]";
    }
  
}
