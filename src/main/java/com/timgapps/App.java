package com.timgapps;

import com.timgapps.model.Director;
import com.timgapps.model.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Film.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // получим список всех фильмов у режисера
//            Director director = session.get(Director.class, 2);
//            List<Film> filmList = director.getFilmList();
//            System.out.println(filmList);

            // получим любой фильм, а затем получим его режиссера
            Film film = session.get(Film.class, 4);
            Director director1 = film.getDirector();
            System.out.println(director1);

            // добавим еще один фильм для любого режиссера
            Director director = session.get(Director.class, 1);
            Film film1 = new Film(director, "New Film for any director");
            director.addFilm(film1);
            session.save(film1);

            // Создадим новый фильм и режиссера и свяжем их
            Director director2 = new Director("New director", 31);
            Film film2 = new Film(director2, "New film for new director");
            director.setFilmList(new ArrayList<Film>(Collections.singletonList(film2)));
            session.save(director2);
            session.save(film2);

            // Сменим режиссера у имеющегося фильма
            Film film3 = session.get(Film.class, 1);
            Director director3 = session.get(Director.class, 2);
            film3.getDirector().getFilmList().remove(film3);
            film3.setDirector(director3);
            director3.getFilmList().add(film3);

            // Удалим фильм у любого режиссера
            Director director4 = session.get(Director.class, 5);
            session.remove(director4);
            director4.getFilmList().forEach(i -> i.setDirector(null));

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
