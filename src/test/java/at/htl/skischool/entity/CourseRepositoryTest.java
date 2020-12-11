package at.htl.skischool.entity;

import at.htl.skischool.entity.Course;
import at.htl.skischool.entity.Skistudent;
import at.htl.skischool.repository.CourseRepository;
import at.htl.skischool.repository.SkistudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class CourseRepositoryTest {

    static CourseRepository repo;
    static SkistudentRepository repoSki;

    @BeforeAll
    static void beforeAll(){
        repo = new CourseRepository();
        repoSki = new SkistudentRepository();
    }

    @Test
    void addSkiKurs(){

        repo.courseList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        repo.save(course);

        assertThat(repo.courseList).contains(course);

    }

    @Test
    void updateSkiKurs(){

        repo.courseList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);
        Skistudent schueler = new Skistudent("Hans", "Müller", 10, course);


        repo.save(course);
        repoSki.save(schueler);

        assertThat(course.getMember()).isEqualTo(1);

    }

    @Test
    void updateSkiKurs3People(){

        repo.courseList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);
        Skistudent schueler = new Skistudent("Hans", "Müller", 10, course);
        Skistudent schueler1 = new Skistudent("Peter", "Peter", 15, course);
        Skistudent schueler2 = new Skistudent("Sarah", "Bär", 17, course);


        repo.save(course);
        repoSki.save(schueler);
        repoSki.save(schueler1);
        repoSki.save(schueler2);

        assertThat(course.getMember()).isEqualTo(3);

    }

    @Test
    void deleteSkiKurs(){

        repo.courseList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        repo.save(course);

        String id = course.getName();

        repo.delete(id);

        assertThat(repo.courseList).hasSize(0);
        assertThat(repo.courseList).doesNotContain(course);

    }

    @Test
    void findAll(){

        repo.courseList.clear();

        List<Course> list;
        Course course = new Course("Anfänger05-01-2021", 3);
        Course course1 = new Course("Koenner05-01-2021",2);
        Course course2 = new Course("Profi05-01-2021",1);

        repo.save(course);
        repo.save(course1);
        repo.save(course2);

        list = repo.findAll();

        assertThat(list).hasSize(3);
        assertThat(list).contains(course, course1, course2);

    }

    @Test
    void findById(){

        repo.courseList.clear();

        String id;

        Course course = new Course("Anfänger05-01-2021", 3);
        Course course1 = new Course("Koenner05-01-2021",2);
        Course course2 = new Course("Profi05-01-2021",1);

        repo.save(course);
        repo.save(course1);
        repo.save(course2);


        id = course.getName();

        Course k = repo.findById(id);

        assertThat(k).isEqualTo(course);

    }

}
