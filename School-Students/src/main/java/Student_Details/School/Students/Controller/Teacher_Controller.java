package Student_Details.School.Students.Controller;

import Student_Details.School.Students.Model.Teachers;
import Student_Details.School.Students.Service.Teacher_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teacher")
public class Teacher_Controller {
 @Autowired
 Teacher_Service teacher1;


  @PostMapping("/create")
  public ResponseEntity<?> createTeacher(@RequestBody Teachers t1){
        try{
        teacher1.createTeacher(t1);
        return new ResponseEntity<>("Teacher Account Created Successfully", HttpStatus.OK);
        }
        catch(Exception e){
        return new ResponseEntity<>("Teacher Account Creation Failed",HttpStatus.BAD_REQUEST);
        }
        }

  @PostMapping("/login")
  public ResponseEntity<?> loginTeacher(@RequestParam String UserName, @RequestParam String Password){
  try{
  teacher1.loginTeacher(UserName, Password);
  return new ResponseEntity<>("Login Successful",HttpStatus.OK);
  }
  catch(Exception e){
  return new ResponseEntity<>("Login Failed",HttpStatus.BAD_REQUEST);
  }
  }

}
