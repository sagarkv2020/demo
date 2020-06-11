package com.example.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class EmployeeEntity extends BaseEntity {

    @Column(name = "name")
    private String employeeName;
    @Column(name = "salary")
    private int employeeSalary;
    @Column(name = "age")
    private int employeeAge;
    @Column(name = "profile_image")
    private String profileImage;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String employeeName, int employeeSalary, int employeeAge, String profileImage) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
        this.profileImage = profileImage;
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
}