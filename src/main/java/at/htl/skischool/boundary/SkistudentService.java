package at.htl.skischool.boundary;

import at.htl.skischool.entity.Course;
import at.htl.skischool.entity.Skistudent;
import at.htl.skischool.repository.CourseRepository;
import at.htl.skischool.repository.SkiteacherRepository;
import at.htl.skischool.repository.SkistudentRepository;

import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/skistudentService")
public class SkistudentService {

    CourseRepository repoCourse = new CourseRepository();
    SkiteacherRepository repoTeacher = new SkiteacherRepository();
    SkistudentRepository repoStudent = new SkistudentRepository();

    @GET
    @Path("/skiStudent")
    @Produces(MediaType.TEXT_PLAIN)
    public List skiStudent() {

        List<Skistudent> skistudent = repoStudent.findAll();

        return skistudent;

    }


    @POST
    @Path("/newSkistudent")
    @Consumes(MediaType.APPLICATION_JSON)
    public String newSkistudent(JsonValue value) {

        Course course = null;

        List<Skistudent> skistudentList = new ArrayList<>();

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            course = repoCourse.findById(value.asJsonObject().getString("course"));

            Skistudent skistudent = new Skistudent(
                    value.asJsonObject().getString("firstname"),
                    value.asJsonObject().getString("lastname"),
                    value.asJsonObject().getInt("age"),
                    course
            );

            repoStudent.save(skistudent);

            if (value.asJsonObject().containsKey("id")){
                skistudent.setId(value.asJsonObject().getInt("id"));
            }

            return value.toString();
        } else {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                if (repoCourse.findById(value.asJsonArray().get(i).asJsonObject().getString("course")) != null){
                course = repoCourse.findById(value.asJsonArray().get(i).asJsonObject().getString("course"));
                }else {
                    return "Gewünschter Kurs nicht vorhanden!";
                }

                Skistudent skistudent = new Skistudent(
                        value.asJsonArray().get(i).asJsonObject().getString("firstname"),
                        value.asJsonArray().get(i).asJsonObject().getString("lastname"),
                        value.asJsonArray().get(i).asJsonObject().getInt("age"),
                        course
                );

                skistudentList.add(skistudent);

                repoStudent.save(skistudent);

                if (value.asJsonArray().get(i).asJsonObject().containsKey("id")){
                    skistudent.setId(value.asJsonArray().get(i).asJsonObject().getInt("id"));
                }

            }

            return skistudentList.toString();

        }


    }


    @DELETE
    @Path("/skiStudentDelete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String skiStudentDelete(JsonValue value) {

        StringBuilder elements = new StringBuilder();

        int id;

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            id = value.asJsonObject().getInt("id");

            return repoStudent.delete(id);

        }else {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                id = value.asJsonArray().get(i).asJsonObject().getInt("id");

                elements.append(repoStudent.delete(id))
                        .append(" ");

            }
            return elements.toString();
        }
    }

    @GET
    @Path("/findSkistudent")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String findSkistudent(JsonValue value) {

        int id;
        List<Skistudent> skistudent = new ArrayList<>();

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            id = value.asJsonObject().getInt("id");

            return repoStudent.findById(id).toString();

        }
        else if (value.getValueType().equals(JsonValue.ValueType.ARRAY)){
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                id = value.asJsonArray().get(i).asJsonObject().getInt("id");

                skistudent.add(repoStudent.findById(id));
            }

            return skistudent.toString();

        }else {
            return "Kein Schüler mit dieser ID gefunden!";
        }

    }

}
