package handson.example.springshopsearch.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
	//主キーを示す
	@Id
	//勝手に実装される
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	//カラム名明示的に
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    //最小値は0と指定している
    @Min(value = 0)
    @Column(name = "price")
    public int price;

    //=TEXTで、256文字制限をなくしている
    @Column(name = "description", columnDefinition = "TEXT")
    public String description;
}
