package at.htl.skischool.boundary;

import at.htl.skischool.entity.Course;
import at.htl.skischool.repository.CourseRepository;
import at.htl.skischool.repository.SkiteacherRepository;
import at.htl.skischool.repository.SkistudentRepository;

import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/courseService")
public class CourseService {

    CourseRepository repoCourse = new CourseRepository();
    SkiteacherRepository repoTeacher = new SkiteacherRepository();
    SkistudentRepository repoStudent = new SkistudentRepository();


    @GET
    @Path("/course")
    @Produces(MediaType.TEXT_PLAIN)
    public List courseString() {

        List<Course> course = repoCourse.findAll();

        return course;

    }

    @POST
    @Path("/newKurs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String newCourse(JsonValue value) {

        List<Course> courseList = new ArrayList<>();

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            Course course = new Course(
                    value.asJsonObject().getString("name"),
                    value.asJsonObject().getInt("aclass")
            );

            repoCourse.save(course);

            return value.toString();
        } else {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                Course course = new Course(
                        value.asJsonArray().get(i).asJsonObject().getString("name"),
                        value.asJsonArray().get(i).asJsonObject().getInt("aclass")
                );

                courseList.add(course);

                repoCourse.save(course);
            }

            return courseList.toString();

        }
    }

    @DELETE
    @Path("/deleteCourse")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteCourse(JsonValue value) {

        StringBuilder elements = new StringBuilder();

        String name;

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            name = value.asJsonObject().getString("name");

            return repoCourse.delete(name);

        }else {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                name = value.asJsonArray().get(i).asJsonObject().getString("name");

                elements.append(repoCourse.delete(name))
                        .append(" ");

            }
            return elements.toString();
        }
    }

    @GET
    @Path("/findCourse")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String findCourse(JsonValue value) {

        String name;
        List<Course> courseList = new ArrayList<>();

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            name = value.asJsonObject().getString("name");

            return repoCourse.findById(name).toString();

        }
        else if (value.getValueType().equals(JsonValue.ValueType.ARRAY)) {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                name = value.asJsonArray().get(i).asJsonObject().getString("name");

                courseList.add(repoCourse.findById(name));
            }

            return courseList.toString();

        } else{
            return "Kein Kurs mit diesem Namen gefunden!";
        }

    }


}
