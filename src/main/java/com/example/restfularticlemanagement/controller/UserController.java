package com.example.restfularticlemanagement.controller;

import com.example.restfularticlemanagement.entity.Article;
import com.example.restfularticlemanagement.entity.Category;
import com.example.restfularticlemanagement.entity.Tag;
import com.example.restfularticlemanagement.entity.User;
import com.example.restfularticlemanagement.exception.UnAuthorizedParamRequestException;
import com.example.restfularticlemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

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
    public String profile(HttpSession session, Model model) {
        Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
        User byId = userService.findById(loggedInUserId);
        model.addAttribute("loggedInUserArticles",byId.getArticleList());
        return "profile";
    }
    @RequestMapping("/pass-edit")
    public String userPassEdit(HttpSession session) {
        return "pass-edit-form";
    }

    @RequestMapping("/pass-process")
    public String userPassProcess(HttpServletRequest request, HttpSession session) {
        Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
        User userById = userService.findById(loggedInUserId);
        String password = request.getParameter("password");
        String encode = passwordEncoder.encode(password);
        userById.setPassword(encode);
        userService.save(userById);
        return  "redirect:/logout";
    }

    @RequestMapping("/add-article")
    public String addArticle(Model model){
        Article newArticle = new Article();
        model.addAttribute("newArticle",newArticle);
        List<Category> allCategories = categoryService.findAll();
        List<Tag> tags = tagService.findAll();
        model.addAttribute("categoryList", allCategories);
        model.addAttribute("tags", tags);
        return "add-article-form";
    }

    @RequestMapping("/insert-article")
    public String insertArticle(@ModelAttribute("newArticle") Article article, HttpSession session){
        Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
        User loggedInUser = userService.findById(loggedInUserId);
        article.setUser(loggedInUser);
        article.setCreateDate(LocalDate.now().toString());
        article.setIsPublished(false);
        articleService.save(article);
        return "redirect:/user/profile";
    }

    @RequestMapping("/edit-article")
    public String editArticle(@RequestParam("id") Integer id, Model model,HttpSession session) {
        Article userArticle = articleService.findById(id);
        User user = userArticle.getUser();
        if (user.getId()!=session.getAttribute("loggedInUserId"))
            throw new UnAuthorizedParamRequestException("You are not allowed to access this article!");
        model.addAttribute("userArticle", userArticle);
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("categoryList", allCategories);
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "article-edit";
    }

    @RequestMapping("/update-article")
    public String updateArticle(@ModelAttribute("userArticle") Article article, HttpServletRequest request) {
        LocalDate now = LocalDate.now();
        String stringUpdateDate = now.toString().substring(0, 10);
        article.setLastUpdateDate(stringUpdateDate);
        articleService.save(article);
        return "redirect:/user/profile";
    }
    @RequestMapping("/delete-article")
    public String deleteArticle(@RequestParam("id") Integer id,HttpSession session) {
        Article userArticle = articleService.findById(id);
        User user = userArticle.getUser();
        if (user.getId()!=session.getAttribute("loggedInUserId"))
            throw new UnAuthorizedParamRequestException("You are not allowed to access this article!");
        articleService.delete(userArticle);
        return "redirect:/user/profile";
    }

}
