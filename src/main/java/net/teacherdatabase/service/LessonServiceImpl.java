package net.teacherdatabase.service;

import net.teacherdatabase.dao.LessonDao;
import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class implements {@link Service @Service} layer for entity Lesson
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    public void setLessonDao(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Transactional
    public void addLesson(Lesson lesson) {
        this.lessonDao.addLesson(lesson);
    }

    @Transactional
    public void updateLesson(Lesson lesson) {
        this.lessonDao.updateLesson(lesson);
    }

    @Transactional
    public void removeLesson(int id) {
        this.lessonDao.removeLesson(id);
    }

    @Transactional
    public Lesson getLessonById(int id) {
        return this.lessonDao.getLessonById(id);
    }

    @Transactional
    public List<Lesson> listLessons() {
        return this.lessonDao.listLessons();
    }

    @Transactional
    public Teacher getTeacherById(int id) {
        return this.lessonDao.getTeacherById(id);
    }
}
