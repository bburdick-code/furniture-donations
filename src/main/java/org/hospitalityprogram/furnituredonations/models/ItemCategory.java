package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ItemCategory extends AbstractEntity {

    @NotBlank(message = "Item name is required")
    private String name;

    @OneToMany(mappedBy = "itemCategory")
    private final List<Item> items = new ArrayList<>();

    public ItemCategory(@NotBlank(message = "Item name is required") String name) {
        this.name = name;
    }

    public ItemCategory() {

    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Item> getItems() { return items; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemCategory that = (ItemCategory) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
