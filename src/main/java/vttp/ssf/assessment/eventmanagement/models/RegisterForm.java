package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterForm {
    
    @NotEmpty(message="Full name cannot be empty")
    @Size(min=5, message="Full name must contain at least 5 characters")
    @Size(max=25, message="Full name is limited up to a maximum of 25 characters")
    private String fullName;

    @Past(message="DOB cannot be greater than or equals to present date")
    private LocalDate dob;

    @NotEmpty(message="Email name cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Size(max=50, message="Email is limited up to maximum of 50 characters")
    private String email;

    @Pattern(regexp = "^[89]\\d{7}$")
    private String mobileNumber;

    private String gender;

    @Min(value=1, message="Minimum of 1 ticket")
    @Max(value=3, message="Maximum of 3 tickets")
    private int numberOfTickets;
    
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getNumberOfTickets() {
        return numberOfTickets;
    }
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
    
}