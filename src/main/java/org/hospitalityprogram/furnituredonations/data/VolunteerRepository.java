package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.VolunteerProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends CrudRepository<VolunteerProfile, Integer> {

    @Query(value = "SELECT * FROM VOLUNTEER_PROFILE WHERE user_id = :userId ", nativeQuery = true)
    VolunteerProfile findByUserId(@Param("userId") Integer userId);
}
