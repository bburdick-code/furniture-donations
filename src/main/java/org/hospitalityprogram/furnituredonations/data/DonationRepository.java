package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.Donation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {

}
