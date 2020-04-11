package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.StudentProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentProfile, Integer> {

    @Query(value = "select * from STUDENT_PROFILE where user_id = :userId ", nativeQuery = true)
    StudentProfile findByUserId(@Param("userId") Integer userId);
}
