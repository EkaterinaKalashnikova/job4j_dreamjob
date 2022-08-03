package ru.job4j.dream.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@ThreadSafe
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/fail")
    public  String regFail() {
        return "redirect:/registrationPage?fail=true";
    }

    @GetMapping("/success")
    public  String regSuccess() {
        return "redirect:/index";
    }

    @GetMapping("/registration")
    public String addUser(Model model) {
        model.addAttribute("user", new User(0, "Заполните поле", "Заполните поле", "Заполните поле"));
        return "registration";
    }

    @GetMapping("/registrationPage")
    public String registrationPage(Model model, HttpSession session, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "registration";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, HttpSession session, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByEmailAndPassword(
                user.getEmail(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

   /* @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }*/
}
/*1. Доработайте информацию о текущем пользователе в HttpSession.

2. Добавьте вывод текущего пользователя на все html страницы в шапке.

3. Добавьте кнопку выхода из аккаунта на страницы.

 */
