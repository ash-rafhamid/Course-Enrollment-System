package com.example.course_enrollment_system.dto;

public class StudentRequest {
    private String name;
    private String email;

    public String getname(){return name;};
    public String getemail(){return email;};
    public void setname(String name){this.name = name;};
    public void setemail(String email){this.email = email;};
}
