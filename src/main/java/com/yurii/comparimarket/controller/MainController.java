package com.yurii.comparimarket.controller;


import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yurii.comparimarket.model.Item;
import com.yurii.comparimarket.model.ListItem;
import com.yurii.comparimarket.service.SeleniumManager;

@Controller
public class MainController {
	
    SeleniumManager seleniumManager = new SeleniumManager();
    
    @GetMapping({"/","/index"})
    public String mainPage(Model model){
        model.addAttribute("item", new Item());
        return "index";
    }
    
    @PostMapping("/index")
    public String getItems(Model model, @ModelAttribute Item item){
    	List<String> firstItem = seleniumManager.getFromATB(item.getItemName());
    	List<String> secondItem = seleniumManager.getFromSilpo(item.getItemName());
    	List<String> thirdItem = seleniumManager.getFromVarus(item.getItemName());
        try {
        	model.addAttribute("ATBItemName",firstItem.get(0));
        	model.addAttribute("ATBItemPrice",firstItem.get(1));
        }
        catch(IndexOutOfBoundsException e) {
        	model.addAttribute("ATBItemName","unavailable");
        	model.addAttribute("ATBItemPrice","unavailable");
        }
        try {
        	model.addAttribute("SilpoItemName",secondItem.get(0));
        	model.addAttribute("SilpoItemPrice",secondItem.get(1));
        }
        catch(IndexOutOfBoundsException e) {
        	model.addAttribute("SilpoItemName","unavailable");
        	model.addAttribute("SilpoItemPrice","unavailable");
        }
        try {
        	model.addAttribute("VarusItemName",thirdItem.get(0));
        	model.addAttribute("VarusItemPrice",thirdItem.get(1));
        }
        catch(IndexOutOfBoundsException e) {
        	model.addAttribute("VarusItemName","unavailable");
        	model.addAttribute("VarusItemPrice","unavailable");
        }
        
        return "index";
    }

}
