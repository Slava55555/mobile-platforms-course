/**
 * Лекционный курс - наследник Course
 * Уникальный параметр: количество лекционных часов
 */
public class LectureCourse extends Course {
    private int lectureHours; // лекционные часы - уникальный параметр
    private boolean hasExam; // экзамен или зачет

    public LectureCourse(String courseCode, String courseName, int creditUnits,
                         String instructor, int totalHours, int lectureHours, boolean hasExam) {
        super(courseCode, courseName, creditUnits, instructor, totalHours);
        this.lectureHours = lectureHours;
        this.hasExam = hasExam;
    }

    public int getLectureHours() {
        return lectureHours;
    }

    public boolean hasExam() {
        return hasExam;
    }

    @Override
    public String getCourseType() {
        return "Лекционный курс";
    }

    @Override
    public String getAssessmentType() {
        return hasExam ? "Экзамен" : "Зачет";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof LectureCourse)) return false;

        LectureCourse that = (LectureCourse) obj;
        return lectureHours == that.lectureHours && hasExam == that.hasExam;
    }

    @Override
    public String toString() {
        return String.format("%s - %s [Лекций: %d ч, Аттестация: %s]",
                getCourseType(), super.toString(), lectureHours, getAssessmentType());
    }
}