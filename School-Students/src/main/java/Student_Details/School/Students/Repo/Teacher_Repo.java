package Student_Details.School.Students.Repo;

import Student_Details.School.Students.Model.Teachers;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface Teacher_Repo extends JpaRepository<Teachers, Integer> {
    boolean existsByUserName(String userName);

    Teachers findByUserName(String userName);

    boolean existsByemailId(String emailId);
}
