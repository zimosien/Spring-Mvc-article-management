package com.example.restfularticlemanagement.controller;

import com.example.restfularticlemanagement.entity.*;
import com.example.restfularticlemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@Secured(value = "ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private TagServiceImpl tagService;
    @Autowired
    @Qualifier("customEncoder")
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/profile")
    public String adminProfile() {
        return "admin/profile";
    }

//    Article Section

    @RequestMapping("/articles")
    public String allArticles(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("allArticles", articles);
        return "admin/articles";
    }

    @RequestMapping("/edit-article")
    public String editArticle(@RequestParam("id") Integer id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("categoryList", allCategories);
        return "admin/article-edit";
    }

    @RequestMapping("/update-article")
    public String updateArticle(@ModelAttribute("article") Article article) {
        LocalDate now = LocalDate.now();
        String stringUpdateDate = now.toString().substring(0, 10);
        if (article.getPublishDate() == null || article.getPublishDate().trim().equals("")) {
            if (article.getIsPublished())
                article.setPublishDate(stringUpdateDate);
        } else if (article.getPublishDate() != null) {
            if (!article.getIsPublished())
                article.setPublishDate(null);
        }
        article.setLastUpdateDate(stringUpdateDate);
        articleService.save(article);
        return "redirect:/admin/articles";
    }

    @RequestMapping("/delete-article")
    public String deleteArticle(@RequestParam("id") Integer id) {
        Article article = articleService.findById(id);
        articleService.delete(article);
        return "redirect:/admin/profile";
    }

//    User Section

    @RequestMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @RequestMapping("/edit-user")
    public String editUser(@RequestParam("id") Integer id, Model model) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "admin/edit-user";
    }

    @RequestMapping("/update-user")
    public String updateUser(@ModelAttribute("user")User newUser) {
        Integer userId = newUser.getId();
        User user = userService.findById(userId);
        user.setRoles(newUser.getRoles());
        userService.save(user);
        return "redirect:/admin/users";
    }
    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Integer id) {
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect:/admin/users";
    }

//    Category Section

    @RequestMapping("/categories")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "admin/categories";
    }

    @RequestMapping("/insert-category")
    public String insertCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category",category);
        return "admin/insert-category";
    }

    @RequestMapping("/edit-category")
    public String editCategory(@RequestParam("id") Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/edit-category";
    }

    @RequestMapping("/save-category")
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") Integer id) {
        Category category = categoryService.findById(id);
        categoryService.delete(category);
        return "redirect:/admin/categories";
    }

    //  Role Section

    @RequestMapping("/roles")
    public String showRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles",roles);
        return "admin/roles";
    }

    @RequestMapping("/insert-role")
    public String insertRole(Model model) {
        Role role = new Role();
        model.addAttribute("role",role);
        return "admin/insert-role";
    }

    @RequestMapping("/edit-role")
    public String editRole(@RequestParam("id") Integer id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "admin/edit-role";
    }

    @RequestMapping("/save-role")
    public String saveCategory(@Valid @ModelAttribute("role") Role role,
                               BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()){
            String origin = request.getParameter("origin");
           if(origin.equals("insert"))
               return "admin/insert-role";
           return "admin/edit-role";
        }
        String theRoleTitle ="ROLE_".concat(role.getTitle().toUpperCase());
        role.setTitle(theRoleTitle);
        roleService.save(role);
        return "redirect:/admin/roles";
    }

    @RequestMapping("/delete-role")
    public String deleteRole(@RequestParam("id") Integer id) {
        Role role = roleService.findById(id);
        roleService.delete(role);
        return "redirect:/admin/roles";
    }
//  Tags Section
    @RequestMapping("/tags")
    public String showTags(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags",tags);
        return "admin/tags";
    }

    @RequestMapping("/insert-tag")
    public String insertTag(Model model) {
        Tag tag = new Tag();
        model.addAttribute("tag",tag);
        return "admin/insert-tag";
    }

    @RequestMapping("/edit-tag")
    public String editTag(@RequestParam("id") Integer id, Model model) {
        Tag tag = tagService.findById(id);
        model.addAttribute("tag", tag);
        return "admin/edit-tag";
    }

    @RequestMapping("/save-tag")
    public String saveTag(@ModelAttribute("role") Tag tag) {
        tagService.save(tag);
        return "redirect:/admin/tags";
    }

    @RequestMapping("/delete-tag")
    public String deleteTag(@RequestParam("id") Integer id) {
        Tag tag = tagService.findById(id);
        tagService.delete(tag);
        return "redirect:/admin/tags";
    }

}














