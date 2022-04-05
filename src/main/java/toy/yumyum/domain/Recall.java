package toy.yumyum.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Recall {
	@Id
	@GeneratedValue
	private Long id;

	private String reason;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "food_id")
	private Food food;
}
