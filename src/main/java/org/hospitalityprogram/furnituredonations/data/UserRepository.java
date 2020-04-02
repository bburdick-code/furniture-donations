package org.hospitalityprogram.furnituredonations.data;

import org.hospitalityprogram.furnituredonations.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
