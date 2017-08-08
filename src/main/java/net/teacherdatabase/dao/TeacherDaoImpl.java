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
 * Class implements DAO layer for entity Teacher
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {

    private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTeacher(Teacher teacher) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(teacher);
        logger.info("Teacher successfully saved. Teacher details: " + teacher);
    }

    public void updateTeacher(Teacher teacher) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(teacher);
        logger.info("Teacher successfully update. Teacher details: " + teacher);
    }

    public void removeTeacher(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Teacher teacher = (Teacher) session.load(Teacher.class, new Integer(id));
        if (teacher != null) {
            session.delete(teacher);
        }
        logger.info("Teacher successfully removed. Teacher details: " + teacher);
    }

    public Teacher getTeacherById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Teacher teacher = (Teacher) session.load(Teacher.class, new Integer(id));
        logger.info("Teacher successfully loaded. Teacher details: " + teacher);
        return teacher;
    }

    @SuppressWarnings("unchecked")
    public List<Teacher> listTeachers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Teacher> teacherList = session.createQuery("from net.teacherdatabase.model.Teacher").list();
        for (Teacher teacher : teacherList) {
            logger.info("Teachers list: " + teacher);
        }
        return teacherList;
    }

    public List<Lesson> getListLessonByTeacherId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Lesson> lessonsList = session.load(Teacher.class, new Integer(id)).getLessonList();
        for (Lesson lesson : lessonsList) {
            logger.info("Lessons list: " + lesson);
        }
        return lessonsList;
    }
}
