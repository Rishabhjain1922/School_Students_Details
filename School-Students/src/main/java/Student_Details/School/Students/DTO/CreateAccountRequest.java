package Student_Details.School.Students.DTO;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountRequest {
    private String name;
    private String Department;
    private String PhoneNumber;
    private String userName;
    private String Password;
    private String emailId;
    public CreateAccountRequest() {}
    public CreateAccountRequest(String name, String Department, String PhoneNumber, String userName, String Password, String emailId) {
        this.name = name;
        this.Department = Department;
        this.PhoneNumber = PhoneNumber;
        this.userName = userName;
        this.Password = Password;
        this.emailId = emailId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return Department;
    }
    public void setDepartment(String Department) {
        this.Department = Department;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}