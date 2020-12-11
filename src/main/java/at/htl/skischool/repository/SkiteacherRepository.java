package at.htl.skischool.repository;

import at.htl.skischool.entity.Course;
import at.htl.skischool.entity.Skiteacher;

import java.util.ArrayList;
import java.util.List;

public class SkiteacherRepository implements Repository<Skiteacher>{

    public List<Skiteacher> skiteacherList = new ArrayList<>();

    public SkiteacherRepository() {

        Course course = new Course("Anfänger05-01-2021", 1);

        save(new Skiteacher("Hans", "Müller", 55, course,  1430));
        save(new Skiteacher("Peter", "Hofer", 50, course,  1000));
        save(new Skiteacher("Lisa", "Müller", 25, course,  1000));
    }

    public void save(Skiteacher entity){


        if (entity.getId() == null){
            //SAVE
            entity.setId(skiteacherList.size());
            skiteacherList.add(entity);

        }else {
            //UPDATE
            Skiteacher lehrer = findById(entity.getId());

            lehrer.setFirstname(entity.getFirstname());
            lehrer.setLastname(entity.getLastname());
            lehrer.setAge(entity.getAge());
            lehrer.setCourse(entity.getCourse());
            lehrer.setSalary(entity.getSalary());

        }



//        id = getElement(entity);
//
//        if (id != -1){
//            entity.setId(id);
//        }

    }

    public String delete(int id){

        int idToDelete;

        idToDelete = getIdFromElement(id);
        if (idToDelete != -1){
            skiteacherList.remove(idToDelete);
            renewID();
            return "Gelöscht";
        }

//        for (String i = 0; i < skilehrerList.size(); i++) {
//            skilehrerList.get(i).setId(i);
//        }
        return "Nichts Gelöscht";

    }

    public List<Skiteacher> findAll(){

        return skiteacherList;

    }

    public Skiteacher findById(int id){

        for (Skiteacher s: skiteacherList) {
            if (s.getId() == id){
                return s;
            }
        }

        return null;

    }

    private int getIdFromElement(int id) {

        for (int i = 0; i < skiteacherList.size(); i++) {
            if (skiteacherList.get(i).getId() == id){
                return i;
            }
        }

        return -1;

    }

    private void renewID(){
        for (int i = 0; i < skiteacherList.size(); i++) {
            skiteacherList.get(i).setId(i);
        }
    }

}
