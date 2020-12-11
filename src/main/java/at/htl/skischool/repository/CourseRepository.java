package at.htl.skischool.repository;

import at.htl.skischool.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public List<Course> courseList = new ArrayList<>();

    public CourseRepository() {

        save(new Course("Anfänger05-01-2021", 3));
        save(new Course("Koenner05-01-2021", 2));
        save(new Course("Profis22-12-2020", 1));
    }

    public void save(Course entity){


        if (findById(entity.getName()) == null){
            //SAVE
            courseList.add(entity);
        }


    }

    public String delete(String id){

        int idToDelete;

        idToDelete = getIdFromElement(id);
        if (idToDelete != -1){

            courseList.remove(idToDelete);
            return "Gelöscht";
        }

//        for (String i = 0; i < skilehrerList.size(); i++) {
//            skilehrerList.get(i).setId(i);
//        }
        return "Nichts gelöscht";

    }

    public List<Course> findAll(){

        return courseList;

    }

    public Course findById(String id){

        for (Course s: courseList) {
            if (s.getName().equals(id)){
                return s;
            }
        }

        return null;

    }

    private int getIdFromElement(String id) {

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getName().equals(id)){
                return i;
            }
        }

        return -1;

    }

}
