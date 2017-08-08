package net.teacherdatabase.dao;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class implements DAO layer for entity Lesson
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
@Repository
public class LessonDaoImpl implements LessonDao {

    private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addLesson(Lesson lesson) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(lesson);
        logger.info("Lesson successfully saved. Lesson details: " + lesson);
    }

    public void updateLesson(Lesson lesson) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(lesson);
        logger.info("Lesson successfully update. Lesson details: " + lesson);
    }

    public void removeLesson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Lesson lesson = (Lesson) session.load(Lesson.class, new Integer(id));
        if (lesson != null) {
            session.delete(lesson);
        }
        logger.info("Lesson successfully removed. Lesson details: " + lesson);
    }

    public Lesson getLessonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Lesson lesson = (Lesson) session.load(Lesson.class, new Integer(id));
        logger.info("Lesson successfully loaded. Lesson details: " + lesson);
        return lesson;
    }

    @SuppressWarnings("unchecked")
    public List<Lesson> listLessons() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Lesson> lessonList = session.createQuery("from net.teacherdatabase.model.Lesson").list();
        for (Lesson lesson : lessonList) {
            logger.info("Lesson list: " + lesson);
        }
        return lessonList;
    }

    public Teacher getTeacherById(int id) {
        Teacher teacher = this.sessionFactory.getCurrentSession().find(Teacher.class, id);
        logger.info("Teacher successfully received. Teacher details: " + teacher);
        return teacher;
    }
}
