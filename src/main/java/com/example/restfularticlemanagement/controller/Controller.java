package com.example.restfularticlemanagement.controller;

import com.example.restfularticlemanagement.dto.UserDetailDto;
import com.example.restfularticlemanagement.entity.Category;
import com.example.restfularticlemanagement.entity.Role;
import com.example.restfularticlemanagement.exception.UserNotFoundException;
import com.example.restfularticlemanagement.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import com.example.restfularticlemanagement.entity.Article;
import com.example.restfularticlemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {
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

    @RequestMapping({"/home","/"})
    public String showHome() {
        return "home";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userr", new User());
        return "register";
    }

    @PostMapping("/user-registered")
    public String registeredPage(@ModelAttribute("userr") User user) {
        User byUsername = userService.findByUsername(user.getUserName());
        if (byUsername!=null)
            return "redirect:/register";
        user.setPassword(passwordEncoder.encode(user.getNationalCode()));
        Role role = roleService.findRoleByTitle("ROLE_WRITER");
        user.getRoles().add(role);
        userService.save(user);
        return "redirect:/home";
    }

    @RequestMapping("/articles-list")
    public String articlesPage(Model model) {
        List<Article> all = articleService.findAll();
        model.addAttribute("articlesList", all);
        return "articles";
    }

    @RequestMapping("/article-detail")
    public String articleDetail(@RequestParam("id") Integer id, Model model) {
        Article byId = articleService.findById(id);
        model.addAttribute("article", byId);
        return "article-detail";
    }
    @RequestMapping("/login")
    public String loginPage() {
        return "login-page";
    }

    @RequestMapping("/login-process")
    public String login( HttpSession session, Principal principal) {
        User oneUser = userService.findByUsername(principal.getName());
        if (oneUser!=null) {
            session.setAttribute("loggedInUserId",oneUser.getId());
            return "redirect:/home";
        } else
            throw new UserNotFoundException("requested user does not exist!");
    }

    //    @RequestMapping("/temp")
//    public String temp() {
//        User user = userService.findById(3);
//        Role role = roleService.findById(1);
//        user.getRoles().add(role);
//        userService.save(user);
//        return "home";
//    }
}
