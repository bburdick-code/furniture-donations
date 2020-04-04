package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}
