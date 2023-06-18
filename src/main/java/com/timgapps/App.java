package com.timgapps;

import com.timgapps.model.Director;
import com.timgapps.model.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

            // получим любой фильм, а затем получим его режисера
//            Film film = session.get(Film.class, 4);
//            Director director1 = film.getDirector();
//            System.out.println(director1);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
