package org.hospitalityprogram.furnituredonations.models.dto;

import org.hospitalityprogram.furnituredonations.models.Item;
import org.hospitalityprogram.furnituredonations.models.User;

import javax.validation.constraints.NotNull;

public class UserItemDTO {

    @NotNull
    private User user;

    @NotNull
    private Item item;

    public UserItemDTO() {

    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Item getItem() { return item; }

    public void setItem(Item item) { this.item = item; }
}
