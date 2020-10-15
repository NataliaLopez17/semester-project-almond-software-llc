package com.example.restservice;

import java.util.ArrayList;
import java.util.Random;

import com.example.macademia.Course;
import com.example.macademia.Department;
import com.example.macademia.Matricula;
import com.example.macademia.Section;
import com.example.macademia.Student;
import com.example.macademia.auth.User;

public class MacademiaTest {

    public static final Student[] testData() {
        // TESTING

        // Lets create a few department
        Department FunLand = new Department("The Department of Comedy", "FUNY");
        Department Drama = new Department("The Department of Drama", "DRAM");

        // Create some courses
        Course COM = new Course("Intro to Comedy", FunLand, 3101, 3); // creating a course with a department links it
                                                                      // back to the department.
        Course CL1 = new Course("Clowning I", FunLand, 3401, 3);
        Course CL2 = new Course("Clowning II", FunLand, 3402, 3);
        Course DRK = new Course("Dark Comedy", FunLand, 5102, 4);

        Course DRAM1 = new Course("Acting 1", Drama, 3001, 3);
        Course DRAM2 = new Course("Acting 2", Drama, 3002, 3);

        Course[] AllClasses = { COM, CL1, CL2, DRK, DRAM1, DRAM2 };

        // Prerequesite time
        DRAM2.addPrereq(DRAM1);
        CL1.addPrereq(DRAM2);
        CL1.addPrereq(COM);
        CL2.addPrereq(CL1);
        DRK.addPrereq(CL2);

        // Lets create some sections for these courses
        Random rand = new Random();
        for (Course course : AllClasses) {
            new Section(rand.nextInt(90) + "", "MWF", "5:30-6:30", course); // Creating a section linked to a course
                                                                            // automatically links it back to the
                                                                            // course.
            new Section(rand.nextInt(90) + "", "TJ", "5:30-7:00", course);
            new Section(rand.nextInt(90) + "", "MW", "5:30-7:00", course);
        }

        // Create a user.
        User Person1 = new User("Person1", "Password");
        User Person2 = new User("Person2", "1234");
        User Person3 = new User("Person3", "This is a password that's very long wow que cool");

        // Create some matriculas:
        ArrayList<Course> CourseList1 = new ArrayList<Course>();
        ArrayList<Course> CourseList2 = new ArrayList<Course>();
        ArrayList<Course> CourseList3 = new ArrayList<Course>();

        CourseList1.add(COM);
        CourseList1.add(DRAM1);
        CourseList2.add(COM);
        CourseList2.add(DRAM2);
        CourseList3.add(DRAM1);

        Matricula Mat1 = new Matricula(CourseList1, 6, "FALL");
        Matricula Mat2 = new Matricula(CourseList2, 6, "FALL");
        Matricula Mat3 = new Matricula(CourseList3, 3, "FALL");

        // Create a student.
        Student Stud1 = new Student(Person1, "El Tipillo", "802-55-5555", Mat1, FunLand);
        Student Stud2 = new Student(Person2, "La Tipilla", "802-55-5555", Mat2, FunLand);
        Student Stud3 = new Student(Person3, "Bob", "802-55-5555", Mat3, Drama);

        Student[] AllStudents = { Stud1, Stud2, Stud3 };
        return AllStudents;

        // TESTING
    }

}
