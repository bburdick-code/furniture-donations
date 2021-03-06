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

    private Integer itemPriority;

    private Date itemRequestDate;

    @ManyToOne
    private StudentProfile itemStudent;

    public Item(ItemCategory itemCategory, ItemStatus itemStatus, Integer itemPriority, Date itemRequestDate, StudentProfile itemStudent) {
        this.itemCategory = itemCategory;
        this.itemStatus = itemStatus;
        this.itemPriority = itemPriority;
        this.itemRequestDate = itemRequestDate;
        this.itemStudent = itemStudent;
    }

    public Item(Item other) {
        this.itemCategory = other.itemCategory;
        this.itemStatus = other.itemStatus;
        this.itemPriority = other.itemPriority;
        this.itemRequestDate = other.itemRequestDate;
        this.itemStudent = other.itemStudent;
    }

    public Item() {

    }

    public ItemCategory getItemCategory() { return itemCategory; }

    public void setItemCategory(ItemCategory itemCategory) { this.itemCategory = itemCategory; }

    public ItemStatus getItemStatus() { return itemStatus; }

    public void setItemStatus(ItemStatus itemStatus) { this.itemStatus = itemStatus; }

    public Integer getItemPriority() { return itemPriority; }

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
