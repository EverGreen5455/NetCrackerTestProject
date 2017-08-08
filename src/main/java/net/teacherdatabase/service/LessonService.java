package net.teacherdatabase.service;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface describes {@link Service @Service} layer for entity Lesson
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
public interface LessonService {

    public void addLesson(Lesson lesson);

    public void updateLesson(Lesson lesson);

    public void removeLesson(int id);

    public Lesson getLessonById(int id);

    public List<Lesson> listLessons();

    public Teacher getTeacherById(int id);
}
