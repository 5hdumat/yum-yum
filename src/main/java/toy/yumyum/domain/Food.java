package toy.yumyum.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Food {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String ingredients;

	@OneToMany(mappedBy = "food")
	private List<Recall> recalls = new ArrayList<>();
}
