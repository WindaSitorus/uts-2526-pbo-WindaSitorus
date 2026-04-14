package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.Account;

/**
 * @author 12S24019 Winda N.V. Sitorus
 * 
 */

public class Driver4
 {
        static Lecturer[] lecturers = new Lecturer[0];
        static Course[] courses = new Course[0];
        static Student[] students = new Student[0];
        static Enrollment[] enrollments = new Enrollment[0];
    
        public static void main(String[] _args) {
            Scanner scanner = new Scanner(System.in);
            String line = null;
    
            while (true) {
                line = scanner.nextLine();
                if (line.equals("---")) {
                    break;
                }
    
                String[] data = line.split("#");
                String lecturer_add = "lecturer-add";
                String course_add = "course-add";
                String student_add = "student-add";
                    
            
        if (data[0].compareTo(lecturer_add) == 0) {
            Lecturer newLecturer = arrayToLecturer(data);
                addLecturer(newLecturer);
                    }  
    
        else if (data[0].compareTo(student_add) == 0) {
            Student newStudent = arrayToStudent(data);
                addStudent(newStudent);
                } 
        
        else if (data[0].compareTo(course_add) == 0) {
            Course newCourse = arrayToCourse(data);
                addCourse(newCourse);
                }
            
        else {
            Enrollment newEnrollment = arrayToEnrollment(data);
                addEnrollment(newEnrollment);
                }
            }
            printAllLecturers();
            printAllCourses();
            printAllStudents();
            printAllEnrollments();
            scanner.close();
        }
      
        public static Lecturer checkDuplicateLecturer(String IdLecturer){
            for (Lecturer lecturer : lecturers){
                if (lecturer.getId().equals(IdLecturer)){
                    return lecturer;
                }
            }
            return null;
        }

        private static void printAllLecturers() {
            for (Lecturer lecturer : lecturers) {
                System.out.println(lecturer);
            }
        }
    
        private static void addLecturer(Lecturer lecturer) {
            lecturers = Arrays.copyOf(lecturers, lecturers.length + 1);
            lecturers[lecturers.length - 1] = lecturer;
        }
    
        private static Lecturer arrayToLecturer(String[] data) {
            Lecturer lecturer = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
    
            return lecturer;
        }

        public static Student checkDuplicateStudent(String codeStudent){
            for (Student student : students){
                if (student.getId().equals(codeStudent)){
                    return student;
                }
            }
            return null;
        }

        private static void printAllStudents() {
            for (Student student : students) {
                System.out.println(student);
            }
        }

        private static void printAllCourses() {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    
        private static void addCourse(Course course) {
            courses = Arrays.copyOf(courses, courses.length + 1);
                courses[courses.length - 1] = course;
        }
    
        private static Course arrayToCourse(String[] data) {
            Course course = new Course(data[1], data[2], Integer.parseInt(data[3]), data[4], data[5]);
            return course;
        }
        
        private static void addStudent(Student student) {
            students = Arrays.copyOf(students, students.length + 1);
            students[students.length - 1] = student;
        }
    
        private static Student arrayToStudent(String[] data) {
            Student student = new Student(data[1],data[2],data[3],data[4]);
            return student;
        }

        public static Course checkDuplicateCorCourse(String codeCourse){
            for (Course course : courses){
                if (course.getCode().equals(codeCourse)){
                    return course;
                }
            }
            return null;
        }

        public static Enrollment checkDuplicateEnrollment(String codeEnrollment){
            for (Enrollment enrollment : enrollments){
                // System.out.println(enrollment);
                if (enrollment.getCode().equals(codeEnrollment)){
                    return enrollment;
                }
            }
            return null;
        }

        private static void printAllEnrollments() {
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
        }
    
        private static void addEnrollment(Enrollment enrollment) {
            enrollments = Arrays.copyOf(enrollments, enrollments.length + 1);
            enrollments[enrollments.length - 1] = enrollment;
        }
    
        private static Enrollment arrayToEnrollment(String[] data) {
            Enrollment enrollment = new Enrollment(data[1], data[2], data[3], data[4]);
    
            return enrollment;
        }
    }