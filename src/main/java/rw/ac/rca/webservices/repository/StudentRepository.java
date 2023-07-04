package rw.ac.rca.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.ac.rca.webservices.models.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByAge(int age);
    @Query("from Student where lastName=?1 order by firstName")
    List<Student> findSortedLname(String lname);

    @Query("from Student  order by age")
    List<Student> findByAgeSorted();
}
