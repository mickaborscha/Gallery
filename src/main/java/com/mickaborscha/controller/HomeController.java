package com.mickaborscha.controller;

import com.mickaborscha.entity.Article;
import com.mickaborscha.entity.Category;
import com.mickaborscha.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<Category> categories = this.categoryRepository.findAll();

        model.addAttribute("view", "home/index");
        model.addAttribute("categories", categories);

        return "base-layout";
    }

    @GetMapping("/category/{id}")
    public String listArticles(Model model, @PathVariable Integer id) {
        if (!this.categoryRepository.exists(id)) {
            return "redirect:/";
        }

        Category category = categoryRepository.findOne(id);
        Set<Article> articles = category.getArticles();

        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        model.addAttribute("view", "home/list-articles");

        return "base-layout";
    }

    @GetMapping("/error/403")
    public String accessDenied(Model model) {
        model.addAttribute("view", "error/403");

        return "base-layout";
    }
}
