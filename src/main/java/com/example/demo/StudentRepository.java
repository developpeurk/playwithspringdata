package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

   // JPQL

    @Query("SELECT s FROM Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    // List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, Integer  age);

   @Query("SELECT s FROM Student s where s.firstName = ?1 AND s.age > ?2")
    List<Student> findStudentByFirstNameEqualsAndAgeIsGreaterThanEqual(
            String firstName, Integer  age);



   // Native Query
    @Query(
            value = "SELECT * FROM student WHERE first_name = ?1 AND age >= ?2",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            String firstName, Integer  age);


    // Named Query
    @Query(
            value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNamed(
            @Param("firstName") String firstName,
            @Param("age") Integer  age);


    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentByIdModifying(Long id);

}
