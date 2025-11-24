package com.yurii.comparimarket.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yurii.comparimarket.model.ListItem;
import com.yurii.comparimarket.service.ListService;

@Controller("/list")
public class ListController {
	
	ListService listService = new ListService();
    
    @PostMapping("/save")
    public String saveItemToList(Model model, @ModelAttribute ListItem listItem){
    	listService.addItem(listItem);
    	model.addAttribute("ATBItemName","");
    	model.addAttribute("ATBItemPrice","");
    	model.addAttribute("SilpoItemName","");
    	model.addAttribute("SilpoItemPrice","");
    	model.addAttribute("VarusItemName","");
    	model.addAttribute("VarusItemPrice","");
    	model.addAttribute("fullList",listService.getAllItems());
    	return "index";
    }
    
    @PostMapping("/remove")
    public String removeItemFromList(Model model, @ModelAttribute ListItem listItem){
    	listService.removeItem(listItem);
    	model.addAttribute("ATBItemName","");
    	model.addAttribute("ATBItemPrice","");
    	model.addAttribute("SilpoItemName","");
    	model.addAttribute("SilpoItemPrice","");
    	model.addAttribute("VarusItemName","");
    	model.addAttribute("VarusItemPrice","");
    	model.addAttribute("fullList",listService.getAllItems());
    	return "index";
    }

}
