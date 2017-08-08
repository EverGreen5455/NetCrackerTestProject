package net.teacherdatabase.model;

import javax.persistence.*;

/**
 * Class describes entity Lesson
 *
 * @author Sekachkin Mikhail
 */

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lesson_Name", nullable = false)
    private String lessonName;

    @Column(name = "lesson_Date", nullable = false)
    private String lessonDate;

    @Column(name = "lesson_Time", nullable = false)
    private String lessonTime;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", lessonDate='" + lessonDate + '\'' +
                ", lessonTime='" + lessonTime + '\'' +
                '}';
    }
}
