package net.teacherdatabase.controller;

import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import net.teacherdatabase.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class implements {@link Controller @Controller} layer for entity Lesson
 * contains methods performing basic processing tasks
 *
 * @author Sekachkin Mikhail
 */
@Controller
public class LessonController {

    private LessonService lessonService;

    @Autowired(required = true)
    @Qualifier(value = "lessonService")
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(value = "/lessons/add/{teacherId}", method = RequestMethod.POST)
    public String addLesson(@PathVariable("teacherId") int id, @ModelAttribute("lesson") Lesson lesson) {
        if (lesson.getId() == 0) {
            Teacher teacher = lessonService.getTeacherById(id);
            teacher.getLessonList().add(lesson);
            lesson.setTeacher(teacher);
            this.lessonService.addLesson(lesson);
        } else {
            lesson.setTeacher(lessonService.getTeacherById(id));
            this.lessonService.updateLesson(lesson);
        }
        return "redirect:/schedule/" + id;
    }

    @RequestMapping("/lessons/remove/{id}")
    public String removeLesson(@PathVariable("id") int id) {
        int teacherId = this.lessonService.getLessonById(id).getTeacher().getId();
        this.lessonService.removeLesson(id);
        return "redirect:/schedule/" + teacherId;
    }

    @RequestMapping("lessons/edit/{id}")
    public String editLesson(@PathVariable("id") int id, Model model) {
        model.addAttribute("lesson", this.lessonService.getLessonById(id));
        int teacherId = this.lessonService.getLessonById(id).getTeacher().getId();
        model.addAttribute("listLessons", this.lessonService.getTeacherById(teacherId).getLessonList());
        model.addAttribute("teacherId", teacherId);
        return "lessons";
    }
}
