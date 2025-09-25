/**
 * Лабораторный курс - наследник Course
 * Уникальный параметр: количество лабораторных работ
 */
public class LabCourse extends Course {
    private int labWorks; // лабораторные работы - уникальный параметр
    private String labEquipment;

    public LabCourse(String courseCode, String courseName, int creditUnits,
                     String instructor, int totalHours, int labWorks, String labEquipment) {
        super(courseCode, courseName, creditUnits, instructor, totalHours);
        this.labWorks = labWorks;
        this.labEquipment = labEquipment;
    }

    public int getLabWorks() {
        return labWorks;
    }

    public String getLabEquipment() {
        return labEquipment;
    }

    @Override
    public String getCourseType() {
        return "Лабораторный курс";
    }

    @Override
    public String getAssessmentType() {
        return "Дифференцированный зачет";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof LabCourse)) return false;

        LabCourse that = (LabCourse) obj;
        return labWorks == that.labWorks && labEquipment.equals(that.labEquipment);
    }

    @Override
    public String toString() {
        return String.format("%s - %s [Лаб. работ: %d, Оборудование: %s]",
                getCourseType(), super.toString(), labWorks, labEquipment);
    }
}