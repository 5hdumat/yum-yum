package toy.yumyum.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedFood {

	@Id
	@GeneratedValue
	@Column(name = "feed_foot_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "pet_id")
	private Pet pet;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "food_id")
	private Food food;

	public void changePet(Pet pet) {
		this.pet = pet;
	}

	@Builder
	public FeedFood(Pet pet, Food food) {
		this.food = food;
	}

}
