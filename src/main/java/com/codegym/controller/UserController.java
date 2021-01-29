package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ModelAndView listUsers(@RequestParam("s") Optional<String> keyword, @RequestParam("page") Optional<Integer> page) {
        Page<User> users;
        ModelAndView modelAndView = new ModelAndView("user/list");
        int pageNum = 0;
        if (page.isPresent() && page.get() > 1) {
            pageNum = page.get() - 1;
        }
        PageRequest pageSplitter = new PageRequest(pageNum, 3, new Sort("firstName"));
        if (keyword.isPresent()) {
            users = userService.findAllByFirstNameContaining(keyword.get(), pageSplitter);
            modelAndView.addObject("keyword", keyword.get());
        } else {
            users = userService.findAll(pageSplitter);
        }
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/user-create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("user/add", "user", new User());
    }

    @PostMapping("/user-create")
    public ModelAndView addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/user/add");
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Added");
        return new ModelAndView("redirect:/users");
    }

    @PostMapping("/remove-user")
    public ModelAndView remove(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        userService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Removed");
        return new ModelAndView("redirect:/users");
    }
}
