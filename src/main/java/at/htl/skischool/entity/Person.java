package at.htl.skischool.entity;

//import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

    private Integer id;
    private String firstname;
    private String lastname;
    private int age;
    private Course course;


    public Person() {
    }

    public Person(String firstname, String lastname, int age, Course course) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.course = course;
    }

    protected Person(int id, String firstname, String lastname, int age, Course course) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.course = course;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


}
