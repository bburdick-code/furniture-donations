package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.models.enums.ItemStatus;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item extends AbstractEntity {

    @ManyToOne
    private ItemCategory itemCategory;

    private ItemStatus itemStatus;

    @ManyToMany(mappedBy = "items")
    private final List<User> users = new ArrayList<>();



}
