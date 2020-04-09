package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.data.ItemRepository;
import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.hospitalityprogram.furnituredonations.models.Item;
import org.hospitalityprogram.furnituredonations.models.ItemCategory;
import org.hospitalityprogram.furnituredonations.models.User;
import org.hospitalityprogram.furnituredonations.models.enums.ItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {

    private int itemAllowance = 3;

    private boolean noDuplicates = true;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Nothing Here");
        return "student/index";
    }

    @GetMapping("profile")
    public String renderProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Profile");
        return "student/profile/index";
    }

    @GetMapping("profile/edit")
    public String renderEditProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Edit Info");
        return "student/profile/edit";
    }

    @PostMapping("profile/edit")
    public String processEditProfile(@ModelAttribute User user, @RequestParam int id, HttpSession session, Errors errors, Model model) {
        Optional<User> resultOb = userRepository.findById(id);
        User result = resultOb.get();

        if (user.getUserAddress() != null) { result.setUserAddress(user.getUserAddress()); }
        if (user.getUserPhone() != null) { result.setUserPhone(user.getUserPhone()); }
        if (user.getFirstName() != null) { result.setFirstName(user.getFirstName()); }
        if (user.getLastName() != null) { result.setLastName(user.getLastName()); }
        if (user.getNickname() != null) { result.setNickname(user.getNickname()); }
        if (user.getPersonalEmail() != null) { result.setPersonalEmail(user.getPersonalEmail()); }
        if (user.getCountry() != null) { result.setCountry(user.getCountry()); }
        if (user.getGender() != null) { result.setGender(user.getGender()); }
        if (user.getMaritalStatus() != null) { result.setMaritalStatus(user.getMaritalStatus()); }

        userRepository.save(result);

        return "redirect:/student/profile";
    }

    @GetMapping("items")
    public String items(Model model) {
        model.addAttribute("title", "Requested Items");
        return "student/items/index";
    }

    @GetMapping("items/edit")
    public String renderEditItemsForm(HttpSession session, Model model) {

        model = PrepareEditForm(session, model);

        return "student/items/edit";
    }

    @PostMapping("items/edit")
    public String processEditItemsForm(@RequestParam(required = false) int[] itemCategoryArray, HttpSession session, Model model) {

        if (noDuplicates){
            for (int i = 0; i < itemAllowance ; i++) {
                for (int j = i+1; j < itemAllowance ; j++) {
                    if (itemCategoryArray[i] == itemCategoryArray[j]) {
                        model = PrepareEditForm(session, model);
                        model.addAttribute("errMsg", "Select only one of each item type");
                        return "student/items/edit";
                    }
                }
            }
        }


        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        User user = result.get();

        List<Item> currentItems = user.getItems();
        List<Item> newItems = new ArrayList<>();

        if (itemCategoryArray != null) {
            for (int i=0 ; i < itemAllowance; i++) {
                if (itemCategoryArray[i] != 0) {
                    boolean categoryFound = false;
                    for (Item curItem : currentItems) {
                        if (curItem.getItemCategory().getId() == itemCategoryArray[i]) {
                            curItem.setItemPriority(i+1);
                            itemRepository.save(curItem);
                            categoryFound = true;
                        }
                    }
                    if (!categoryFound) {
                        boolean priorityFound = false;
                        for (Item curItem : currentItems) {
                            if (curItem.getItemPriority() == i+1) {
                                Optional<ItemCategory> resultOb = itemCategoryRepository.findById(itemCategoryArray[i]);
                                curItem.setItemCategory(resultOb.get());
                                curItem.setItemStatus(ItemStatus.REQUESTED);
                                curItem.setItemRequestDate(new Date());
                                itemRepository.save(curItem);
                                priorityFound = true;
                            }
                        }
                        if (!priorityFound) {
                            System.out.println("Created a new item: Priority " + (i+1));
                            Optional<ItemCategory> resultOb = itemCategoryRepository.findById(itemCategoryArray[i]);
                            Item item = new Item(resultOb.get(), ItemStatus.REQUESTED, (i + 1), new Date(), user);
                            user.addItem(item);
                        }
                    }
                }
            }
            for (int i = 0; i < itemAllowance; i++) {
                for (Item curItem : currentItems) {
                    if (itemCategoryArray[i] == 0 && curItem.getItemPriority() == i+1) {
                        user.removeItem(curItem);
                        itemRepository.delete(curItem);
                    }
                }
            }
            userRepository.save(user);
        }

        return "redirect:/student/items";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "student/settings";
    }


    public Model PrepareEditForm(HttpSession session, Model model) {

        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        User user = result.get();
        List<Item> currentItems = user.getItems();
        List<Item> items = new ArrayList<>();

        for (int i = 1; i <= itemAllowance; i++) {
            boolean found = false;
            for (Item curItem : currentItems) {
                if (curItem.getItemPriority() == i) {
                    found = true;
                    items.add(new Item(curItem)); }
            }
            if (!found) {
                Item item = new Item();
                item.setItemStatus(ItemStatus.REQUESTED);
                item.setItemPriority(i);
                items.add(item);
            }
        }

        model.addAttribute("title", "Edit Items");
        model.addAttribute("items", items);
        model.addAttribute(new Item());
        model.addAttribute(new ItemCategory());
        model.addAttribute("categories", itemCategoryRepository.findAll());

        return model;
    }

}
