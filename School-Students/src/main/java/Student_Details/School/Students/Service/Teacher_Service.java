package Student_Details.School.Students.Service;


import Student_Details.School.Students.Model.Teachers;
import Student_Details.School.Students.Repo.Teacher_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Teacher_Service {
    @Autowired
    private Teacher_Repo repo;

    public void createTeacher(Teachers t1) {
    if(repo.existsByUserName((t1.getUserName()))) {
    throw new RuntimeException("Teacher with userName "+t1.getUserName()+" already exists");
    }
    if(t1.getPhoneNumber().length()!=10){
    throw new RuntimeException("Phone number should be of 10 digits");
    }
    repo.save(t1);
    }

    public void loginTeacher(String userName, String Password) {
    if(!repo.existsByUserName(userName)){
    throw new RuntimeException("Teacher with userName "+userName+" not found");
    }
    if(Password.length()<8||userName.length()<1) {
    throw new RuntimeException("Invalid userName or password");
    }
    if(!repo.findByUserName(userName).getPassword().equals(Password)){
    throw new RuntimeException("Invalid userName or password");
    }

}
}
