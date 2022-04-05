package toy.yumyum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	private String nickname;

	@OneToMany(mappedBy = "member")
	private List<Pet> pets = new ArrayList<>();
}
