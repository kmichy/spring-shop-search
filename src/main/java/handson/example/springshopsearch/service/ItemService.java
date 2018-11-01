package handson.example.springshopsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;

	public void listItem(Model model){
////	見せかけを表示する場合
//      List<Item> list = Arrays.asList(
//              new Item(1L, "商品1", 100, "説明1"),
//              new Item(2L, "商品2", 200, "説明2"),
//              new Item(3L, "商品3", 300, "説明3"),
//              new Item(4L, "商品4", 400, "説明4")
//      );
        List<Item> list = itemRepository.findAll();
        model.addAttribute("items", list);
	}

	public void registerItem(Item item) {
	    itemRepository.save(item);
	}
    public void getDetail(Model model, Long id) {
    	model.addAttribute("item", itemRepository.getOne(id));
    }
	public void getEdit(Model model,Long id) {
		model.addAttribute("item", itemRepository.getOne(id));
	}
    public void editItem(Item item){
    	itemRepository.save(item);
    }
}
