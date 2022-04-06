package toy.yumyum.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import toy.yumyum.domain.Food;
import toy.yumyum.domain.Kind;
import toy.yumyum.domain.PetType;
import toy.yumyum.repository.KindRepository;

@SpringBootTest
@Transactional
class KindServiceTest {

	@Autowired
	KindService kindService;
	@Autowired
	KindRepository kindRepository;

	@Test
	@Rollback(false)
	public void 품종등록() throws Exception {
		//given
		Kind kind = Kind.builder()
			.name("브리티쉬 숏헤어")
			.type(PetType.CAT)
			.build();

		// when
		Long kindId = kindService.saveKind(kind);

		// then
		assertEquals(kind, kindRepository.findOne(kindId));
	}

	@Test
	public void 품종중복체크() throws Exception {
		//given
		Kind kind = Kind.builder()
			.name("브리티쉬 숏헤어")
			.type(PetType.CAT)
			.build();

		// when

		// then
		assertThatIllegalStateException().isThrownBy(() -> {
			kindService.saveKind(kind);
		});
	}

	@Test
	public void 사료명빈값체크() throws Exception {
		//given
		Kind kind = Kind.builder()
			.type(PetType.CAT)
			.build();

		// when

		// then
		assertThatIllegalStateException().isThrownBy(() -> {
			kindService.saveKind(kind);
		});
	}
}