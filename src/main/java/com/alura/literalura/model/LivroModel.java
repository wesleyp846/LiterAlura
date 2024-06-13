package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livro_model")
public class LivroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private int downloadCount;

    private String languages;

//    //@ElementCollection
//    @ManyToOne(cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "idioma_model",
//            joinColumns = @JoinColumn(name = "livro_id"),
//            inverseJoinColumns = @JoinColumn(name = "idioma_id")
//    )
//    @JoinColumn(name = "idioma_id", nullable = false)
//    private IdiomaModel languages;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public AutorModel getAutor() {
        return autor;
    }

    public void setAutor(AutorModel autor) {
        this.autor = autor;
    }
}
