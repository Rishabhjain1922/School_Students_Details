package Student_Details.School.Students.Controller;

import Student_Details.School.Students.DTO.CreateAccountRequest;
import Student_Details.School.Students.Model.Teachers;
import Student_Details.School.Students.Service.Teacher_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/teacher")
public class Teacher_Controller {
 @Autowired
 Teacher_Service teacher1;
    private static final Logger logger = LoggerFactory.getLogger(Teacher_Controller.class);

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@RequestBody CreateAccountRequest request) {
        try {
            logger.info("Received create request: {}", request); // Add this
            teacher1.createTeacher(request);
            return ResponseEntity.ok("Teacher Account Created");
        } catch(Exception e) {
            logger.error("Creation failed: {}", e.getMessage()); // Log exception
            return ResponseEntity.badRequest().body(e.getMessage()); // Return error message
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginTeacher(@RequestParam String UserName, @RequestParam String Password) {
        try {
            logger.info("Login attempt for username: {}", UserName);
            logger.debug("Endpoint: /teacher/login called with username: {}", UserName);

            String token = teacher1.loginTeacher(UserName, Password);

            logger.info("Login successful for username: {}", UserName);
            logger.debug("Generated token for {}: {}", UserName, token);

            return new ResponseEntity<>("Login Successful      " + token, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Login failed for username: {}", UserName, e);
            logger.debug("Login failure details - Username: {}, Error: {}", UserName, e.getMessage());

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestParam String emailId) {
        try {
            logger.info("Forget password request for email: {}", emailId);
            logger.debug("Endpoint: /teacher/forgetPassword called for email: {}", emailId);

            teacher1.forgetPassword(emailId);

            logger.info("Password reset email sent to: {}", emailId);
            logger.debug("OTP generated and sent to {}", emailId);

            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Password reset failed for email: {}", emailId, e);
            logger.debug("Password reset failure details - Email: {}, Error: {}", emailId, e.getMessage());

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam int OTP, @RequestParam String emailId, @RequestParam String newPassword) {
        try {
            logger.info("Password reset attempt for email: {}", emailId);
            logger.debug("Reset password params - Email: {}, OTP: {}, NewPassword: {}", emailId, OTP, "***"); // Mask password

            teacher1.resetPassword(OTP, emailId, newPassword);

            logger.info("Password successfully reset for email: {}", emailId);
            logger.debug("Password updated for {}", emailId);

            return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Password reset failed for email: {}", emailId, e);
            logger.debug("Reset failure details - Email: {}, OTP: {}, Error: {}", emailId, OTP, e.getMessage());

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}