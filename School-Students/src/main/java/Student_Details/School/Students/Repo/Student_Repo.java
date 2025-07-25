package Student_Details.School.Students.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import Student_Details.School.Students.Model.Student;

public interface Student_Repo extends JpaRepository<Student,Integer>{}
