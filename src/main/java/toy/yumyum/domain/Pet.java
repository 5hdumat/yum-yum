package toy.yumyum.domain;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

	@Id
	@GeneratedValue
	@Column(name = "pet_id")
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private PetType type;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "kind_id")
	private Kind kind;

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<FeedFood> feedFoods = new ArrayList<>();

	public void addFeedFoods(FeedFood feedFood) {
		if (feedFoods.contains(feedFood)) {
			feedFoods.remove(feedFood);
		}

		feedFoods.add(feedFood);
		feedFood.changePet(this);
	}

	@Builder
	public Pet(String name, PetType type, Member member, Kind kind, FeedFood... feedFoods) {
		this.name = name;
		this.type = type;
		this.member = member;
		this.kind = kind;

		for (FeedFood feedFood : feedFoods) {
			addFeedFoods(feedFood);
		}
	}
}
