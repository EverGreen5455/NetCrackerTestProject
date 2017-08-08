package net.teacherdatabase.controller;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import net.teacherdatabase.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class implements {@link Controller @Controller} layer for entity Teacher
 * contains methods performing basic processing tasks
 *
 * @author Sekachkin Mikhail
 */
@Controller
public class TeacherController {

    private TeacherService teacherService;

    @Autowired(required = true)
    @Qualifier(value = "teacherService")
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public TeacherService getTeacherService() {
        return teacherService;
    }

    @RequestMapping(value = "teachers", method = RequestMethod.GET)
    public String listTeachers(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("listTeachers", this.teacherService.listTeachers());
        return "teachers";
    }

    @RequestMapping(value = "/teachers/add", method = RequestMethod.POST)
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher) {
        if (teacher.getId() == 0) {
            this.teacherService.addTeacher(teacher);
        } else {
            this.teacherService.updateTeacher(teacher);
        }
        return "redirect:/teachers";
    }

    @RequestMapping("remove/{id}")
    public String removeTeacher(@PathVariable("id") int id) {
        this.teacherService.removeTeacher(id);
        return "redirect:/teachers";
    }

    @RequestMapping("edit/{id}")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", this.teacherService.getTeacherById(id));
        model.addAttribute("listTeachers", this.teacherService.listTeachers());
        return "teachers";
    }

    @RequestMapping("schedule/{id}")
    public String scheduleTeacher(@PathVariable("id") int id, Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("listLessons", this.teacherService.getListLessonByTeacherId(id));
        model.addAttribute("teacherId", id);
        return "lessons";
    }
}
