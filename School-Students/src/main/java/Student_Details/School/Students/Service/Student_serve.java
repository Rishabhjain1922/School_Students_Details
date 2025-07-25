package Student_Details.School.Students.Service;

import Student_Details.School.Students.Model.Student;
import Student_Details.School.Students.Repo.Student_Repo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Student_serve {

    private final Student_Repo repo;
    public Student_serve(Student_Repo repo) {
        this.repo = repo;
    }
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public void addStudent(Student s1) {
        repo.save(s1);
    }

    public Optional<Student> getStudent(int id) {
        return repo.findById(id);
    }

    public void updateDetails(int id, Student s1) {
    Optional<Student> ogstudent=repo.findById(id);
    if(!ogstudent.isEmpty()){
        ogstudent.get().setName(s1.getName());
        ogstudent.get().setStandard(s1.getStandard());
        ogstudent.get().setAge(s1.getAge());
        ogstudent.get().setSection(s1.getSection());
        ogstudent.get().setAddress(s1.getAddress());
        ogstudent.get().setGender(s1.getGender());
        repo.save(ogstudent.get());
        }
    else{
    throw new RuntimeException("Student with id "+id+" not found");}
    }

    public void deleteStudent(int id) {
    repo.deleteById(id);
    }
}
