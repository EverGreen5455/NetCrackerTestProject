package net.teacherdatabase.dao;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;

import java.util.List;

/**
 * Interface describes DAO layer for entity Teacher
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
public interface TeacherDao {

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void removeTeacher(int id);

    public Teacher getTeacherById(int id);

    public List<Teacher> listTeachers();

    public List<Lesson> getListLessonByTeacherId(int id);
}
