package at.htl.skischool.boundary;

import at.htl.skischool.entity.Course;
import at.htl.skischool.entity.Skiteacher;
import at.htl.skischool.repository.CourseRepository;
import at.htl.skischool.repository.SkiteacherRepository;
import at.htl.skischool.repository.SkistudentRepository;

import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/skiteacherService")
public class SkiteacherService {

    CourseRepository repoCourse = new CourseRepository();
    SkiteacherRepository repoTeacher = new SkiteacherRepository();
    SkistudentRepository repoStudent = new SkistudentRepository();

    @GET
    @Path("/skiTeacher")
    @Produces(MediaType.TEXT_PLAIN)
    public List skiTeacher() {

        List<Skiteacher> skiteacher = repoTeacher.findAll();

        return skiteacher;

    }

    @POST
    @Path("/newSkiTeacher")
    @Consumes(MediaType.APPLICATION_JSON)
    public String newSkiTeacher(JsonValue value) {

        Course course = null;

        List<Skiteacher> skiteacherList = new ArrayList<>();

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            course = repoCourse.findById(value.asJsonObject().getString("course"));

            Skiteacher skiteacher = new Skiteacher(
                    value.asJsonObject().getString("firstname"),
                    value.asJsonObject().getString("lastname"),
                    value.asJsonObject().getInt("age"),
                    course,
                    value.asJsonObject().getInt("salary")
            );

            repoTeacher.save(skiteacher);

            if (value.asJsonObject().containsKey("id")){
                skiteacher.setId(value.asJsonObject().getInt("id"));
            }

            return value.toString();
        } else {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                if (repoCourse.findById(value.asJsonArray().get(i).asJsonObject().getString("course")) != null){
                    course = repoCourse.findById(value.asJsonArray().get(i).asJsonObject().getString("course"));
                }else {
                    return "Gewünschter Kurs nicht vorhanden!";
                }

                Skiteacher skiteacher = new Skiteacher(
                        value.asJsonArray().get(i).asJsonObject().getString("firstname"),
                        value.asJsonArray().get(i).asJsonObject().getString("lastname"),
                        value.asJsonArray().get(i).asJsonObject().getInt("age"),
                        course,
                        value.asJsonArray().get(i).asJsonObject().getInt("salary")
                );

                skiteacherList.add(skiteacher);

                repoTeacher.save(skiteacher);

                if (value.asJsonArray().get(i).asJsonObject().containsKey("id")){
                    skiteacher.setId(value.asJsonArray().get(i).asJsonObject().getInt("id"));
                }

            }

            return skiteacherList.toString();

        }


    }

    @DELETE
    @Path("/deleteSkiteacher")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteSkiteacher(JsonValue value) {

        StringBuilder elements = new StringBuilder();

        int id;

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            id = value.asJsonObject().getInt("id");

            return repoTeacher.delete(id);

        }else {
            for (int i = 0; i < value.asJsonArray().size(); i++) {

                id = value.asJsonArray().get(i).asJsonObject().getInt("id");

                elements.append(repoTeacher.delete(id))
                        .append(" ");

            }
            return elements.toString();
        }
    }

    @GET
    @Path("/findSkiteacher")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String findSkiteacher(JsonValue value) {

        int id;
        List<Skiteacher> skiteacherList = new ArrayList<>();

        if (value.getValueType().equals(JsonValue.ValueType.OBJECT)) {

            id = value.asJsonObject().getInt("id");

            return repoTeacher.findById(id).toString();

        }
        else if (value.getValueType().equals(JsonValue.ValueType.ARRAY)){
 //           for (int i = 0; i < value.asJsonArray().size(); i++) {

                id = value.asJsonArray().get(0).asJsonObject().getInt("id");

                skiteacherList.add(repoTeacher.findById(id));
//            }

            return skiteacherList.toString();

        }else {
            return "Kein Lehrer mit dieser ID gefunden!";
        }

    }

}
