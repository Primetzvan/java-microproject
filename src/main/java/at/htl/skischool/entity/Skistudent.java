package at.htl.skischool.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Skistudent extends Person{

    public Skistudent(String firstname, String lastname, int age, Course course) {
        super(firstname, lastname, age, course);
    }

    protected Skistudent(int id, String firstname, String lastname, int age, Course course) {
        super(id, firstname, lastname, age, course);

    }

    public Skistudent() {
    }

    @Override
    public String toString() {
        return "Person mit der " +
                "id " + super.getId() + '\'' +
                ", namens " + super.getFirstname() +
                " " + super.getLastname() +
                ", mit dem alter " + super.getAge() +
                ", befindet sich im Kurs " + super.getCourse().getName() +
                "\n";
    }

}
