package main.repository;

import main.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    //Optional<Student> findByFacultyNumber(String facultyNumber);
//
    //Optional<Student> findByEmail(String email);
//
    //List<Student> findByLastNameContainingIgnoreCase(String lastName);
}
