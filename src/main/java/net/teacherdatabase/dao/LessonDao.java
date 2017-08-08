package net.teacherdatabase.dao;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;

import java.util.List;

/**
 * Interface describes DAO layer for entity Lesson
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
public interface LessonDao {

    public void addLesson(Lesson lesson);

    public void updateLesson(Lesson lesson);

    public void removeLesson(int id);

    public Lesson getLessonById(int id);

    public List<Lesson> listLessons();

    public Teacher getTeacherById(int id);
}
