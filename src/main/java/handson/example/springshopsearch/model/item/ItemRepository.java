package handson.example.springshopsearch.model.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//<DB名、主キーの型(idがlong型なのでlongを指定している)>
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContainsOrderByIdAsc(String keyword);
    List<Item> findByDescriptionContainsOrderByIdAsc(String keyword);
    List<Item> findByDescriptionOrNameContainsOrderByIdAsc(String keyword,String keyword2);
}