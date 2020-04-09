package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.models.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/actions/itemCategory")
public class AdminItemCategoryController {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @GetMapping()
    public String renderViewItemCats(Model model) {
        model.addAttribute("title", "All Item Categories");
        model.addAttribute("categories", itemCategoryRepository.findAll());
        return "admin/actions/itemCategory/index";
    }

    @GetMapping("add")
    public String renderAddItemCatForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new ItemCategory());
        return "admin/actions/itemCategory/add";
    }

    @PostMapping("add")
    public String processAddItemCatForm(@ModelAttribute @Valid ItemCategory newItemCategory, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new ItemCategory());
            model.addAttribute("errorMsg", "Bad data!");
            return "admin/actions/itemCategory/add";
        }
        itemCategoryRepository.save(newItemCategory);
        return "redirect:/admin/actions/itemCategory";
    }



}
