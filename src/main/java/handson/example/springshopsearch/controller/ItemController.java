package handson.example.springshopsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public String listItem(Model model) {
    	itemService.listItem(model);
        return "list_item";
    }

    @GetMapping("add")
    public String getForm() {
        return "item_form";
    }

    @PostMapping("/add")
    public String registerItem(Item item) {
    	itemService.registerItem(item);
        return "redirect:/items";
    }

    @GetMapping("{id:[0-9]+}")
    public String getDetail(Model model, @PathVariable("id") Long id) {
    	itemService.getDetail(model,id);
        return "detail";
    }

    @GetMapping("edit/{id:[0-9]+}")
    public String getEdit(Model model, @PathVariable("id") Long id) {
    	itemService.getEdit(model,id);
        return "edit";
    }
    @PostMapping("/edit/{id:[0-9]+}")
    public String editItem(Item item){
    	itemService.editItem(item);
    	return "redirect:/items";
    }
}