package net.teacherdatabase.service;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface describes {@link Service @Service} layer for entity Teacher
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
public interface TeacherService {

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void removeTeacher(int id);

    public Teacher getTeacherById(int id);

    public List<Teacher> listTeachers();

    public List<Lesson> getListLessonByTeacherId(int id);
}
