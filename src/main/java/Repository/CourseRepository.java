package Repository;

import Entity.Course;
import Entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class CourseRepository {


    public void save(Course course){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void delete (Course course){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Course course){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(course);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public Course getCourseById(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(Course.class, id);
        }
    }

    public List<Course> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Course> cq = cb.createQuery(Course.class);
            Root<Course> root = cq.from(Course.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }
}
