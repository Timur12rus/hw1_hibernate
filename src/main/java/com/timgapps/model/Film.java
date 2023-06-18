package com.timgapps.model;


import javax.persistence.*;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;

    @Column(name = "title")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Film(Director director, String title) {
        this.director = director;
        this.title = title;
    }

    public Film() {
    }


    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", director=" + director +
                ", title='" + title + '\'' +
                '}';
    }
}
