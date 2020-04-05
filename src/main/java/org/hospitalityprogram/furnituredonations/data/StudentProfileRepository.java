package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.StudentProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends CrudRepository<StudentProfile, Integer> {
}
