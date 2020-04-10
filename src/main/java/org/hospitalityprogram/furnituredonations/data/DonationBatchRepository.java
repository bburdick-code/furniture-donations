package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.DonationBatch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationBatchRepository extends CrudRepository<DonationBatch, Integer> {

}
