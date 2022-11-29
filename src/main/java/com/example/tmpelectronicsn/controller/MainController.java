package com.example.tmpelectronicsn.controller;

import com.example.tmpelectronicsn.security.CurrentUser;
import com.example.tmpelectronicsn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/myAccount")
    public String myAccount(ModelMap map, @AuthenticationPrincipal CurrentUser currentUser) {
        map.addAttribute("user", currentUser.getUser());
        return "my-account";
    }

    @GetMapping("/wishlist")
    public String myWishlist() {
        return "wishlist";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/myShopList")
    public String shopList() {
        return "shop-list";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/productDetails")
    public String productDetails() {
        return "product-details";
    }

}
