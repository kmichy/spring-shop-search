package handson.example.springshopsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/")
public class HomeController {

//①
//    @GetMapping
//    public String getIndex() {
//        return "index";
//    }

    @GetMapping("about")
    public String getAbout() {
        return "about";
    }


//②
//    @Autowired
//    ItemRepository itemRepository;
//
//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("items", itemRepository.findAll());
//        return "index";
//    }

	//③
	@Autowired
    ItemRepository itemRepository;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "keyword", required = false) Optional<String> keyword
          , @RequestParam(name = "radiocheck", required = false) Optional<String> radiocheck) {
    	List<Item> list;

		if(radiocheck.isPresent()) {
            model.addAttribute("radiocheck",radiocheck.get());
			if(keyword.isPresent()){
    			if(radiocheck.get().equals("商品名")) {
        			list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
        		}else if(radiocheck.get().equals("商品説明")) {
        			list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
        		}else {
        			list = itemRepository.findByDescriptionContainsOrNameContainsOrderByIdAsc(keyword.get(),keyword.get());
                    model.addAttribute("radiocheck","商品名または商品説明");
        		}
    		}else {
        		list = itemRepository.findAll();
    		}
    	}else {
            model.addAttribute("radiocheck","商品名または商品説明");
    		if(keyword.isPresent()) {
    			list = itemRepository.findByDescriptionContainsOrNameContainsOrderByIdAsc(keyword.get(),keyword.get());
    		}else {
        		list = itemRepository.findAll();
    		}
    	}

        model.addAttribute("items", list);
        return "index";
    }
}
