package ru.kpfu.itis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.web.auth.CookieUtil;
import ru.kpfu.itis.web.auth.JwtUtil;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ui")
public class UserController {

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderRegisterUserPage() {
        return "main";
    }

    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        userService.registerUser(userDto);
        return "success";
    }

    @GetMapping("/users")
    public String renderAllUsersPage(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
        System.out.println(redirect);
        Map<String, String> logPas = new HashMap<>();
        List<UserDto> users = userService.getAll();
        for (UserDto u : users) {
            logPas.put(u.getName(),u.getPassword());
        }
        if (username == null || !logPas.containsKey(username) || !logPas.get(username).equals(password)){
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }

        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        JwtUtil.invalidateRelatedTokens(httpServletRequest);
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "redirect:/ui/login";
    }

}
