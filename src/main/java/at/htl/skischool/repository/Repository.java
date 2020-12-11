package at.htl.skischool.repository;

import java.util.List;

public interface Repository<T> {

        public void save(T entity);
        public String delete(int id);
        public List<T> findAll();
        public T findById(int id);

}
