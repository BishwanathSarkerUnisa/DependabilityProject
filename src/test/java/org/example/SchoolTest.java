package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

    @Test
    void testTeacherSalary() {
        Teacher t = new Teacher("T1", "Mr. White", "Chemistry", 5);
        assertEquals(75000, t.calculateSalary(5));
        assertEquals("Chemistry", t.getDepartment());
        assertEquals("Mr. White", t.getName());
        assertEquals("T1", t.getId());
        assertEquals(5, t.getCourseCount());
    }

    @Test
    void testTeacherBaseSalary() {
        Teacher t = new Teacher("T2", "Newbie", "Math", 0);
        assertEquals(50000, t.calculateSalary(0));
    }

    @Test
    void testStudentData() {
        Student s = new Student("S1", "John", "Doe", 2023, "CSE");
        assertEquals("S1", s.getId());
        assertEquals("John", s.getFirstName());
        assertEquals("Doe", s.getLastName());
        assertEquals(2023, s.getYear());
        assertEquals("CSE", s.getProgramme());

        // Test Empty Constructor
        Student empty = new Student();
        assertNull(empty.getId());
    }

    @Test
    void testServiceFlow() {
        SchoolService service = new SchoolService();

        Student s = new Student("S5", "Alice", "Wonderland", 2022, "Art");
        String res1 = service.addStudent(s);
        assertEquals("Student added: Alice", res1);
        assertNotNull(service.getStudentById("S5"));

        Teacher t = new Teacher("T5", "Snape", "Potions", 10);
        String res2 = service.addTeacher(t);
        assertEquals("Teacher added: Snape", res2);

        int salary = service.getTeacherSalary("T5");
        assertEquals(100000, salary);
    }

    // --- NEW TESTS FOR 100% COVERAGE ---
    @Test
    void testNullInputs() {
        SchoolService service = new SchoolService();

        // Try adding student with null ID
        Student badStudent = new Student(null, "Ghost", "Buster", 2020, "None");
        String err1 = service.addStudent(badStudent);
        assertEquals("Error: ID cannot be null", err1);

        // Try adding teacher with null ID
        Teacher badTeacher = new Teacher(null, "Ghost", "Teacher", 0);
        String err2 = service.addTeacher(badTeacher);
        assertEquals("Error: ID cannot be null", err2);

        // Try getting salary for non-existent teacher
        int salary = service.getTeacherSalary("FAKE_ID");
        assertEquals(0, salary);
    }

    @Test
    void testGetLists() {
        SchoolService service = new SchoolService();
        service.addStudent(new Student("1", "A", "B", 1, "C"));
        service.addTeacher(new Teacher("2", "X", "Y", 1));

        List<Student> students = service.getAllStudents();
        List<Teacher> teachers = service.getAllTeachers();

        assertEquals(1, students.size());
        assertEquals(1, teachers.size());
    }
}