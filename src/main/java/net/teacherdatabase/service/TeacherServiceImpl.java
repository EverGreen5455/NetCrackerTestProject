package net.teacherdatabase.service;

import net.teacherdatabase.dao.TeacherDao;
import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class implements {@link Service @Service} layer for entity Teacher
 * contains CRUD methods
 *
 * @author Sekachkin Mikhail
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Transactional
    public void addTeacher(Teacher teacher) {
        this.teacherDao.addTeacher(teacher);
    }

    @Transactional
    public void updateTeacher(Teacher teacher) {
        this.teacherDao.updateTeacher(teacher);
    }

    @Transactional
    public void removeTeacher(int id) {
        this.teacherDao.removeTeacher(id);
    }

    @Transactional
    public Teacher getTeacherById(int id) {
        return this.teacherDao.getTeacherById(id);
    }

    @Transactional
    public List<Teacher> listTeachers() {
        return this.teacherDao.listTeachers();
    }

    @Transactional
    public List<Lesson> getListLessonByTeacherId(int id) {
        return this.teacherDao.getListLessonByTeacherId(id);
    }
}
