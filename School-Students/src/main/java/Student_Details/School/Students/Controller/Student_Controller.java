package Student_Details.School.Students.Controller;
import Student_Details.School.Students.Model.Student;
import Student_Details.School.Students.Service.Student_serve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Carmel")
public class Student_Controller {

    private final Student_serve student1;
    @Autowired
    public Student_Controller(Student_serve student1) {
        this.student1 = student1;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        try{
            List<Student> std1=student1.getAllStudents();
            return new ResponseEntity<>(std1,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student s1){
        System.out.println("Received request to add student: " + s1);
        try{
            student1.addStudent(s1);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable int id){
        try{
            Optional<Student> s1=student1.getStudent(id);
            return new ResponseEntity<>(s1,HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id,@RequestBody Student s1){
        try{
            student1.updateDetails(id,s1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
    try{
    student1.deleteStudent(id);
    return new ResponseEntity<>("Successfully deleted student with id: " + id,HttpStatus.OK);
    }
    catch(Exception e){
    return  new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
    }
    }
}