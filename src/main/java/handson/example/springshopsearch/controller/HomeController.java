package handson.example.springshopsearch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import handson.example.springshopsearch.model.item.ItemRepository;
import handson.example.springshopsearch.service.HomeService;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("about")
    public String getAbout() {
        return "about";
    }

	@Autowired
    ItemRepository itemRepository;

//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("items", itemRepository.findAll());
//        return "index";
//    }


	@Autowired
	HomeService homeService;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "keyword", required = false) Optional<String> keyword
          , @RequestParam(name = "radiocheck", required = false) Optional<String> radiocheck) {
    	homeService.index(model,keyword,radiocheck);

        return "index";
    }
}
