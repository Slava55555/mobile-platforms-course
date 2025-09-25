/**
 * Абстрактный класс учебного курса для российской системы образования
 * Общий параметр: трудоемкость в зачетных единицах (creditUnits)
 */
public abstract class Course implements Comparable<Course> {
    protected String courseCode;
    protected String courseName;
    protected int creditUnits; // зачетные единицы - общий параметр для сравнения
    protected String instructor;
    protected int totalHours; // общее количество часов

    public Course(String courseCode, String courseName, int creditUnits,
                  String instructor, int totalHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditUnits = creditUnits;
        this.instructor = instructor;
        this.totalHours = totalHours;
    }

    public abstract String getCourseType();

    public abstract String getAssessmentType(); // тип аттестации

    public int getCreditUnits() {
        return creditUnits;
    }

    public int getTotalHours() {
        return totalHours;
    }

    // Расчет часов в неделю (по семестру 17 недель)
    public double getHoursPerWeek() {
        return totalHours / 17.0;
    }

    @Override
    public int compareTo(Course other) {
        return Integer.compare(this.creditUnits, other.creditUnits);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Course that = (Course) obj;
        return creditUnits == that.creditUnits &&
                totalHours == that.totalHours &&
                courseCode.equals(that.courseCode) &&
                courseName.equals(that.courseName) &&
                instructor.equals(that.instructor);
    }

    @Override
    public String toString() {
        return String.format("%s - %s [ЗЕ: %d, Часы: %d]",
                courseCode, courseName, creditUnits, totalHours);
    }
}