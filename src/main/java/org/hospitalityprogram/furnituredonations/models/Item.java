package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.models.enums.ItemStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Item extends AbstractEntity {

    @ManyToOne
    private ItemCategory itemCategory;

    private ItemStatus itemStatus;

    private int itemPriority;

    private Date itemRequestDate;

    @ManyToMany(mappedBy = "items", cascade = CascadeType.ALL)
    private final List<User> users = new ArrayList<>();

    public Item(ItemCategory itemCategory, ItemStatus itemStatus, int itemPriority, Date itemRequestDate) {
        this.itemCategory = itemCategory;
        this.itemStatus = itemStatus;
        this.itemPriority = itemPriority;
        this.itemRequestDate = itemRequestDate;
    }

    public Item() {

    }

    public ItemCategory getItemCategory() { return itemCategory; }

    public void setItemCategory(ItemCategory itemCategory) { this.itemCategory = itemCategory; }

    public ItemStatus getItemStatus() { return itemStatus; }

    public void setItemStatus(ItemStatus itemStatus) { this.itemStatus = itemStatus; }

    public int getItemPriority() { return itemPriority; }

    public void setItemPriority(int itemPriority) { this.itemPriority = itemPriority; }

    public List<User> getUsers() { return users; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return itemPriority == item.itemPriority &&
                itemCategory.equals(item.itemCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemCategory, itemPriority);
    }
}
