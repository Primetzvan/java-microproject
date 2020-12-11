package at.htl.skischool.entity;

import at.htl.skischool.repository.SkiteacherRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class SkiteacherRepositoryTest {

    static SkiteacherRepository repo;

    @BeforeAll
    static void beforeAll(){
        repo = new SkiteacherRepository();
    }

    @Test
    void addSkiTeacher(){

        repo.skiteacherList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        Skiteacher teacher = new Skiteacher("Hans", "Müller", 55, course,  1430);

        repo.save(teacher);

        assertThat(repo.skiteacherList).contains(teacher);

    }

    @Test
    void updateSkiTeacher(){

        Integer id;

        repo.skiteacherList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        Skiteacher teacher = new Skiteacher("Hans", "Müller", 55, course,  1430);

        repo.save(teacher);

        id = teacher.getId();
        Skiteacher teachernew = new Skiteacher(id, "Hans", "Müller", 60, course,  1430);

        repo.save(teachernew);

        assertThat(repo.skiteacherList.get(0).getAge()).isEqualTo(teachernew.getAge());

    }

    @Test
    void deleteSkiTeacher(){

        repo.skiteacherList.clear();
        Course course = new Course("Anfänger05-01-2021", 3);

        Skiteacher teacher = new Skiteacher("Hans", "Müller", 55, course,  1430);

        repo.save(teacher);

        Integer id = teacher.getId();

        repo.delete(id);

        assertThat(repo.skiteacherList).hasSize(0);
        assertThat(repo.skiteacherList).doesNotContain(teacher);

    }

    @Test
    void findAll(){

        repo.skiteacherList.clear();

        List<Skiteacher> list;
        Course course = new Course("Anfänger05-01-2021", 3);

        Skiteacher skiteacher = new Skiteacher("Hans", "Müller", 55, course,  1430);
        Skiteacher skiteacher1 = new Skiteacher("Peter", "Hofer", 50, course,  1000);
        Skiteacher skiteacher2 = new Skiteacher("Lisa", "Müller", 25, course,  1000);

        repo.save(skiteacher);
        repo.save(skiteacher1);
        repo.save(skiteacher2);

        list = repo.findAll();

        assertThat(list).hasSize(3);
        assertThat(list).contains(skiteacher, skiteacher1, skiteacher2);

    }

    @Test
    void findById(){

        repo.skiteacherList.clear();

        Integer id;
        Skiteacher teacher;
        Course course = new Course("Anfänger05-01-2021", 3);

        Skiteacher skiteacher = new Skiteacher("Hans", "Müller", 55, course,  1430);
        Skiteacher skiteacher1 = new Skiteacher("Peter", "Hofer", 50, course,  1000);
        Skiteacher skiteacher2 = new Skiteacher("Lisa", "Müller", 25, course,  1000);

        repo.save(skiteacher);
        repo.save(skiteacher1);
        repo.save(skiteacher2);

        id = skiteacher.getId();

        teacher = repo.findById(id);

        assertThat(teacher).isEqualTo(skiteacher);

    }

}
