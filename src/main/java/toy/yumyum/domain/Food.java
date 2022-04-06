package toy.yumyum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
	@Id
	@GeneratedValue
	@Column(name = "food_id")
	private Long id;

	private String name;

	private String ingredients;

	@Enumerated(EnumType.STRING)
	private PetType type;

	@OneToMany(mappedBy = "food")
	private List<Recall> recalls = new ArrayList<>();

	@Builder
	public Food(String name, String ingredients, PetType type) {
		this.name = name;
		this.ingredients = ingredients;
		this.type = type;
	}
}
