package com.springdemo.laba5.controllers;

import com.springdemo.laba5.entities.Category;
import com.springdemo.laba5.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Показать все категории
    @GetMapping
    public String showCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    // Показать форму для добавления категории
    @GetMapping("/new")
    public String showNewCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "new-category";
    }

    // Сохранить новую категорию
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }
}
