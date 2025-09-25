/**
 * Практический курс - наследник Course
 * Уникальный параметр: количество практических занятий
 */
public class PracticalCourse extends Course {
    private int practicalClasses; // практические занятия - уникальный параметр
    private String requiredMaterials;

    public PracticalCourse(String courseCode, String courseName, int creditUnits,
                           String instructor, int totalHours, int practicalClasses,
                           String requiredMaterials) {
        super(courseCode, courseName, creditUnits, instructor, totalHours);
        this.practicalClasses = practicalClasses;
        this.requiredMaterials = requiredMaterials;
    }

    public int getPracticalClasses() {
        return practicalClasses;
    }

    public String getRequiredMaterials() {
        return requiredMaterials;
    }

    @Override
    public String getCourseType() {
        return "Практический курс";
    }

    @Override
    public String getAssessmentType() {
        return "Зачет с оценкой";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof PracticalCourse)) return false;

        PracticalCourse that = (PracticalCourse) obj;
        return practicalClasses == that.practicalClasses &&
                requiredMaterials.equals(that.requiredMaterials);
    }

    @Override
    public String toString() {
        return String.format("%s - %s [Практических: %d, Материалы: %s]",
                getCourseType(), super.toString(), practicalClasses, requiredMaterials);
    }
}