package com.example.demo.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseVO {

    private int id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private int employeeSalary;
    @JsonProperty("employee_age")
    private int employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public EmployeeResponseVO() {

    }

    public static class EmployeeBuilder {
        private int id;
        private String employeeName;
        private int employeeSalary;
        private int employeeAge;
        private String profileImage;

        public EmployeeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public EmployeeBuilder employeeSalary(int employeeSalary) {
            this.employeeSalary = employeeSalary;
            return this;
        }

        public EmployeeBuilder employeeAge(int employeeAge) {
            this.employeeAge = employeeAge;
            return this;
        }

        public EmployeeBuilder profileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public EmployeeResponseVO build() {
            return new EmployeeResponseVO(this.id, this.employeeName, this.employeeSalary, this.employeeAge, this.profileImage);
        }
    }


    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public EmployeeResponseVO(int id, String employeeName, int employeeSalary, int employeeAge, String profileImage) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
        this.profileImage = profileImage;
    }
}

