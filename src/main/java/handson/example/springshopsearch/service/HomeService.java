package handson.example.springshopsearch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Service
public class HomeService {

	@Autowired
	ItemRepository itemRepository;

	public void index(Model model, Optional<String> keyword, Optional<String> radiocheck) {
		List<Item> list;
	    String error = "商品名または商品説明";
		if(radiocheck.isPresent()) {
	        model.addAttribute("radiocheck",radiocheck.get());
			if(keyword.isPresent()){
				if(radiocheck.get().equals("商品名")) {
	    			list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
	    		}else if(radiocheck.get().equals("商品説明")) {
	    			list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
	    		}else {
	    			list = itemRepository.findByDescriptionContainsOrNameContainsOrderByIdAsc(keyword.get(),keyword.get());
	                model.addAttribute("radiocheck",error);
	    		}
			}else {
	    		list = itemRepository.findAll();
			}
		}else {
	        model.addAttribute("radiocheck",error);
			if(keyword.isPresent()) {
				list = itemRepository.findByDescriptionContainsOrNameContainsOrderByIdAsc(keyword.get(),keyword.get());
			}else {
	    		list = itemRepository.findAll();
			}
		    model.addAttribute("items", list);
		}
	}
}
