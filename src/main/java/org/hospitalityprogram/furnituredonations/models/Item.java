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

    public Item(ItemCategory itemCategory, ItemStatus itemStatus) {
        this.itemCategory = itemCategory;
        this.itemStatus = itemStatus;
    }

    public Item() {

    }

    public ItemCategory getItemCategory() { return itemCategory; }

    public void setItemCategory(ItemCategory itemCategory) { this.itemCategory = itemCategory; }

    public ItemStatus getItemStatus() { return itemStatus; }

    public void setItemStatus(ItemStatus itemStatus) { this.itemStatus = itemStatus; }

    public List<User> getUsers() { return users; }
}
