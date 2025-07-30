package Student_Details.School.Students.Service;


import Student_Details.School.Students.DTO.CreateAccountRequest;
import Student_Details.School.Students.Model.Teachers;
import Student_Details.School.Students.Repo.Teacher_Repo;
import Student_Details.School.Students.Util.JavaUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class Teacher_Service {

    HashMap<String,Integer> map=new HashMap<>();
    @Autowired
    private Teacher_Repo repo;

    @Autowired
    private JavaUtil javaUtils;

    @Autowired
    private MailService mailService;

    @Autowired
    private ModelMapper modelMapper;
    public void createTeacher(CreateAccountRequest request) {
    if(repo.existsByUserName((request.getUserName()))) {
    throw new RuntimeException("Teacher with userName "+request.getUserName()+" already exists");
    }
    if(request.getPhoneNumber().length()!=10){
    throw new RuntimeException("Phone number should be of 10 digits");
    }
        Teachers teacher = new Teachers();
        teacher.setName(request.getName());
        teacher.setDepartment(request.getDepartment());
        teacher.setPhoneNumber(request.getPhoneNumber()); // Only works if entity field is String
        teacher.setUserName(request.getUserName());
        teacher.setPassword(request.getPassword());
        teacher.setEmailId(request.getEmailId());

        repo.save(teacher);
    }

    public String loginTeacher(String userName, String Password) {
    if(!repo.existsByUserName(userName)){
    throw new RuntimeException("Teacher with userName "+userName+" not found");
    }
    if(Password.length()<8||userName.length()<1) {
    throw new RuntimeException("Invalid userName or password");
    }
    if(!repo.findByUserName(userName).getPassword().equals(Password)){
    throw new RuntimeException("Invalid userName or password");
    }
    Teachers t1=repo.findByUserName(userName);
    String token=javaUtils.generateToken(t1);
    return token;
    }

    public void forgetPassword(String emailId) {
    if(!repo.existsByemailId(emailId)){
    throw new RuntimeException("Teacher with emailId "+emailId+" not found");
    }
        Random rd=new Random();
        int otp=1000+rd.nextInt(9000);
        map.put(emailId,otp);
        mailService.sendOtpEmail(emailId,String.valueOf(otp));
    }

    public void resetPassword(int otp, String emailId, String newPassword) {
    if(!map.containsKey(emailId)){
    throw new RuntimeException("Invalid emailId");
    }
    if(map.get(emailId)!=otp){
    throw new RuntimeException("Invalid otp");
    }
    Teachers t1=repo.findByemailId(emailId);
    t1.setPassword(newPassword);
    repo.save(t1);
    map.remove(emailId);
    }
}

