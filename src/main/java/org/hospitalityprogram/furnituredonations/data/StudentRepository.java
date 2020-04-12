package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.StudentProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentProfile, Integer> {

    @Query(value = "SELECT * " +
            "FROM student_profile " +
            "WHERE user_id = :userId ", nativeQuery = true)
    StudentProfile findByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT * " +
            "FROM student_profile " +
            "WHERE volunteer_id = :volunteerId ", nativeQuery = true)
    List<StudentProfile> findByVolunteerId(@Param("volunteerId") Integer volunteerId);

    @Query(value = "SELECT * " +
            "FROM student_profile " +
            "WHERE volunteer_id = :volunteerId OR volunteer_id IS NULL", nativeQuery = true)
    List<StudentProfile> findUnclaimedAndMine(@Param("volunteerId") Integer volunteerId);

    @Query(value = "SELECT DISTINCT t1.* " +
            "FROM student_profile t1 " +
            "LEFT JOIN student_profile_items t2 ON t2.student_profile_id = t1.id " +
            "WHERE t2.student_profile_id IS NOT NULL", nativeQuery = true)
    List<StudentProfile> findAllWithItems();
}
