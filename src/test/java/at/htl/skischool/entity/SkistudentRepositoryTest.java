package at.htl.skischool.entity;

import at.htl.skischool.repository.SkistudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class SkistudentRepositoryTest {

    static SkistudentRepository repo;

    @BeforeAll
    static void beforeAll(){
        repo = new SkistudentRepository();
    }

    @Test
    void addSkiStudent(){

        repo.skistudentList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        Skistudent student = new Skistudent("Hans", "Müller", 10, course);

        repo.save(student);

        assertThat(repo.skistudentList).contains(student);

    }

    @Test
    void updateSkiStudent(){

        Integer id;

        repo.skistudentList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        Skistudent student = new Skistudent("Hans", "Müller", 10, course);

        repo.save(student);

        id = student.getId();
        Skistudent studentnew = new Skistudent(id,"Hans", "Müller", 11, course);

        repo.save(studentnew);

        assertThat(repo.skistudentList).contains(student);

    }

    @Test
    void deleteSkiStudent(){

        repo.skistudentList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        Skistudent student = new Skistudent("Hans", "Müller", 10, course);

        repo.save(student);

        Integer id = student.getId();

        repo.delete(id);

        assertThat(repo.skistudentList).hasSize(0);
        assertThat(repo.skistudentList).doesNotContain(student);

    }

    @Test
    void findAll(){

        repo.skistudentList.clear();

        List<Skistudent> list;
        Course course = new Course("Anfänger05-01-2021", 3);

        Skistudent student = new Skistudent("Hans", "Müller", 10, course);
        Skistudent student1 = new Skistudent("Peter", "Hofer", 50, course);
        Skistudent student2 = new Skistudent("Lisa", "Müller", 25, course);

        repo.save(student);
        repo.save(student1);
        repo.save(student2);

        list = repo.findAll();

        assertThat(list).hasSize(3);
        assertThat(list).contains(student,student1,student2);

    }

    @Test
    void findById(){

        repo.skistudentList.clear();

        Integer id;
        Skistudent s;
        Course course = new Course("Anfänger05-01-2021", 3);

        Skistudent student = new Skistudent("Hans", "Müller", 10, course);
        Skistudent student1 = new Skistudent("Peter", "Hofer", 50, course);
        Skistudent student2 = new Skistudent("Lisa", "Müller", 25, course);

        repo.save(student);
        repo.save(student1);
        repo.save(student2);

        id = student.getId();

        s = repo.findById(id);

        assertThat(s).isEqualTo(student);

    }

}
