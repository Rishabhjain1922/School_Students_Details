package Student_Details.School.Students.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Teachers")
public class Teachers {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="Teacher_Name", nullable=false)
private String name;

@Column(name="Department")
private String Department;

@Column(name="Phone_Number", nullable=false)
private String PhoneNumber;

@Column(name="userName", nullable=false)
private String userName;

@Column(name="Password", nullable=false)
private String Password;

public Teachers() {}
public Teachers(int id, String name, String Department, String PhoneNumber, String userName, String Password) {
    this.id = id;
    this.name = name;
    this.Department = Department;
    this.PhoneNumber = PhoneNumber;
    this.userName = userName;
    this.Password = Password;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
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
}
