package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.models.enums.ItemStatus;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Item extends AbstractEntity {

    @ManyToOne
    private ItemCategory itemCategory;

    private ItemStatus itemStatus;

    private int itemPriority;

    private Date itemRequestDate;

    @ManyToOne
    private User itemUser;

    public Item(ItemCategory itemCategory, ItemStatus itemStatus, int itemPriority, Date itemRequestDate, User itemUser) {
        this.itemCategory = itemCategory;
        this.itemStatus = itemStatus;
        this.itemPriority = itemPriority;
        this.itemRequestDate = itemRequestDate;
        this.itemUser = itemUser;
    }

    public Item(Item other) {
        this.itemCategory = other.itemCategory;
        this.itemStatus = other.itemStatus;
        this.itemPriority = other.itemPriority;
        this.itemRequestDate = other.itemRequestDate;
    }

    public Item() {

    }

    public ItemCategory getItemCategory() { return itemCategory; }

    public void setItemCategory(ItemCategory itemCategory) { this.itemCategory = itemCategory; }

    public ItemStatus getItemStatus() { return itemStatus; }

    public void setItemStatus(ItemStatus itemStatus) { this.itemStatus = itemStatus; }

    public int getItemPriority() { return itemPriority; }

    public void setItemPriority(int itemPriority) { this.itemPriority = itemPriority; }

    public Date getItemRequestDate() { return itemRequestDate; }

    public void setItemRequestDate(Date itemRequestDate) { this.itemRequestDate = itemRequestDate; }

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
