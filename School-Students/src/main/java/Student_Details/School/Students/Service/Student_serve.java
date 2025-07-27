package Student_Details.School.Students.Service;

import Student_Details.School.Students.Model.Student;
import Student_Details.School.Students.Model.Teachers;
import Student_Details.School.Students.Repo.Student_Repo;
import Student_Details.School.Students.Repo.Teacher_Repo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import Student_Details.School.Students.DTO.StudentDetails;
import Student_Details.School.Students.DTO.StudentResponse;

@Service
public class Student_serve {

    private final Student_Repo repo;
    private final Teacher_Repo repot;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public Student_serve(Student_Repo repo, Teacher_Repo repot) {
        this.repo = repo;
        this.repot = repot;
    }


    public List<Student> getAllStudents() {
        return repo.findAll();
    }
    
    public List<StudentResponse> getAllStudentResponses(int id ) {
        List<Student> students = repo.findAllbyTeacherId(id);
        List<StudentResponse> responses=students.stream()
                .map(s -> modelMapper.map(s, StudentResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    public void addStudent(StudentDetails s,int id ) {
        Student s1=modelMapper.map(s, Student.class);
        Teachers t=repot.findById(id).get();
        s1.setTeacher(t);
        repo.save(s1);
    }

    public StudentResponse getStudent(int id,int tid ) {
        Optional<Student> s1 = repo.findById(id);
        if(!s1.isEmpty()&&s1.get().getTeacher().getId()!=tid){
        throw new RuntimeException("Student id you are trying to access does not belong to teacher");
        }
        if (s1.isPresent()) {
          return modelMapper.map(s1.get(), StudentResponse.class);
        }
        else{
            throw new RuntimeException("Student with id "+id+" not found");
         }
    }
    public void updateDetails(int id, StudentDetails s1,int tid ) {
    Optional<Student> ogstudent=repo.findById(id);
    if(ogstudent.isPresent()&&ogstudent.get().getTeacher().getId()!=tid){
        throw new RuntimeException("Student id you are trying to access does not belong to teacher");
    }
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

    public void deleteStudent(int id,int tid ) {
    Optional<Student> ogstudent=repo.findById(id);
    if(ogstudent.isPresent()&&ogstudent.get().getTeacher().getId()!=tid){
        throw new RuntimeException("Student id you are trying to access does not belong to teacher");
    }
    repo.deleteById(id);
    }
}
