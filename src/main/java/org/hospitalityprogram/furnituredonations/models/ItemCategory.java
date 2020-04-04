package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
}
