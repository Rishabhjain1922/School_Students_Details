package Student_Details.School.Students.DTO;

import Student_Details.School.Students.Model.Teachers;
import org.springframework.stereotype.Component;


@Component
public class StudentDetails {
    private String name;
    private int age;
    private String gender;
    private String address;
    private int standard;
    private String section;
    private int marks;
    private String PhoneNumber;
    public StudentDetails() {}
    public StudentDetails(String name, int age, String gender, String address, int standard, String section, int marks, String PhoneNumber) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.standard = standard;
        this.section = section;
        this.marks = marks;
        this.PhoneNumber = PhoneNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getStandard() {
        return standard;
    }
    public void setStandard(int standard) {
        this.standard = standard;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
}
