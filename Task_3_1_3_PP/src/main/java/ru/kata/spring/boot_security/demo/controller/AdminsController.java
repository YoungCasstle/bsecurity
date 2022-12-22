package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.getUserByName(principal.getName()));
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("rolesList", roleService.getAll());
        model.addAttribute("newUser", new User());
        return "admin";
    }


    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id,
                         @RequestParam String[] roles1) {
        List<Role> listroles = new ArrayList<>();
        for (String s : roles1) {
            listroles.add(roleService.getByName(s));
        }
        user.setRoles(listroles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user, @RequestParam String[] roles1) {
        List<Role> listroles = new ArrayList<>();
        for (String s : roles1) {
            listroles.add(roleService.getByName(s));
        }
        user.setRoles(listroles);
        userService.addUser(user);
        return "redirect:/admin";
    }
}

