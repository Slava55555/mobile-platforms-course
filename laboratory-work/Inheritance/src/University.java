import java.util.*;
/*
Author: Buchnev Vyacheslav 02421
tg: @SlavaBuchnev
project: https://github.com/Slava55555/mobile-platforms-course
*/

public class University {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();

        // Добавление курсов
        courses.add(new LectureCourse("ИМИТ-001", "Математический анализ", 5, "Иванов И.И.", 170, 68, true)); // 5 ЗЕ, 170 часов

        courses.add(new LectureCourse("ИСТФАК-001", "История", 2, "Кузнецова А.В.", 68, 34, false)); // Зачет

        courses.add(new PracticalCourse("ИМИТ-011", "Программирование", 4, "Петров П.П.", 136, 34, "Ноутбук, PyCharm"));

        courses.add(new PracticalCourse("ИФИЯМ-105", "Английский язык", 3, "Смирнова О.Л.", 102, 51, "Учебник, словарь"));

        courses.add(new LabCourse("ФИЗФАК-001", "Физика", 3, "Сидоров С.С.", 102, 10, "Лаборатория механики"));


        System.out.println("=== УЧЕБНЫЕ КУРСЫ ===");
        printCourses(courses);

        // Сортировка по зачетным единицам
        Collections.sort(courses);

        System.out.println("\n=== КУРСЫ ОТСОРТИРОВАНЫ ПО ЗАЧЕТНЫМ ЕДИНИЦАМ ===");
        printCourses(courses);

        // Группировка по типам курсов
        System.out.println("\n=== ГРУППИРОВКА ПО ТИПАМ КУРСОВ ===");
        groupCoursesByType(courses);

        // Анализ нагрузки
        System.out.println("\n=== АНАЛИЗ УЧЕБНОЙ НАГРУЗКИ ===");
        analyzeWorkload(courses);
    }

    private static void printCourses(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.printf("%d. %s [%.1f ч/нед]%n",
                    i + 1, course, course.getHoursPerWeek());
        }
    }

    private static void groupCoursesByType(List<Course> courses) {
        Map<String, List<Course>> grouped = new HashMap<>();

        for (Course course : courses) {
            String type = course.getCourseType();
            grouped.putIfAbsent(type, new ArrayList<>());
            grouped.get(type).add(course);
        }

        for (Map.Entry<String, List<Course>> entry : grouped.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (Course course : entry.getValue()) {
                System.out.println("  - " + course);
            }
        }
    }

    private static void analyzeWorkload(List<Course> courses) {
        int totalUnits = courses.stream().mapToInt(Course::getCreditUnits).sum();
        int totalHours = courses.stream().mapToInt(Course::getTotalHours).sum();
        double weeklyHours = courses.stream().mapToDouble(Course::getHoursPerWeek).sum();

        System.out.printf("Общее количество зачетных единиц: %d%n", totalUnits);
        System.out.printf("Общее количество часов: %d%n", totalHours);
        System.out.printf("Недельная нагрузка: %.1f часов/неделю%n", weeklyHours);
        System.out.printf("Соотношение: 1 ЗЕ = %.1f часов%n", totalHours / (double)totalUnits);

        System.out.println("\nРекомендации по ФГОС:");
        System.out.println("- Нормальная нагрузка: 54-60 ЗЕ в год");
        System.out.println("- Максимальная нагрузка: 30 ЗЕ в семестр");
    }
}