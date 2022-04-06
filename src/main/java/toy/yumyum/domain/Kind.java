package toy.yumyum.domain;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class Kind {

	@Id
	@GeneratedValue
	@Column(name = "kind_id")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "kind")
	private List<Pet> pets = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private PetType type;

	@Builder
	public Kind(String name, PetType type) {
		this.name = name;
		this.type = type;
	}
}
