package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<ItemCategory, Integer> {

}
