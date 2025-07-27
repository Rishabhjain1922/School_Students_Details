package Student_Details.School.Students.Controller;
import Student_Details.School.Students.DTO.StudentDetails;
import Student_Details.School.Students.Model.Student;
import Student_Details.School.Students.Service.Student_serve;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Student_Details.School.Students.DTO.StudentResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Carmel")
public class Student_Controller {


    @Autowired
    private StudentResponse studentResponse;

    @Autowired
    private StudentDetails studentDetails;

    private final Student_serve student1;
    @Autowired
    public Student_Controller(Student_serve student1) {
        this.student1 = student1;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> getStudents(HttpServletRequest request) {
        try {
            int id=(Integer)request.getAttribute("teacherId");
            List<StudentResponse> responses = student1.getAllStudentResponses(id);
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody StudentDetails s1,HttpServletRequest request){
        try{
            int id=(Integer)request.getAttribute("teacherId");
            student1.addStudent(s1,id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable int id,HttpServletRequest request){
        try{
            int teacherId=(Integer)request.getAttribute("teacherId");
            StudentResponse s1=student1.getStudent(id,teacherId);
            return new ResponseEntity<>(s1,HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id,@RequestBody StudentDetails s1,HttpServletRequest request){
        int teacherId=(Integer)request.getAttribute("teacherId");
        try{
            student1.updateDetails(id,s1,teacherId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id,HttpServletRequest request){
        int teacherId=(Integer)request.getAttribute("teacherId");
    try{
    student1.deleteStudent(id,teacherId);
    return new ResponseEntity<>("Successfully deleted student with id: " + id,HttpStatus.OK);
    }
    catch(Exception e){
    return  new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
    }
    }
}