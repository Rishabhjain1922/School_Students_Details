package Student_Details.School.Students.Model;
import jakarta.persistence.*;



@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="Student_Name", nullable=false)
    private String name;

    @Column(name="Standard", nullable=false)
    private int standard;

    @Column(name="Age", nullable=false)
    private int age;

    @Column(name="Section", nullable=false)
    private String section;

    @Column(name="address", nullable=false)
    private String address;

    @Column(name="gender")
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teachers teacher;

    @Column(name="Marks")
    private int marks;

    @Column(name="Parents_Phone_Number")
    private String PhoneNumber;


    public Student() {}


    public Student(int id, String name, int standard, int age, String section, String address, String gender, Teachers teacher, int marks, String PhoneNumber) {
        this.id = id;
        this.name = name;
        this.standard = standard;
        this.age = age;
        this.section = section;
        this.address = address;
        this.gender = gender;
        this.teacher = teacher;
        this.marks = marks;
        this.PhoneNumber = PhoneNumber;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStandard() { return standard; }
    public void setStandard(int standard) { this.standard = standard; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
    public String getPhoneNumber() { return PhoneNumber; }
    public void setPhoneNumber(String PhoneNumber) { this.PhoneNumber = PhoneNumber; }
    public Teachers getTeacher() { return teacher; }
    public void setTeacher(Teachers teacher) { this.teacher = teacher; }
}