package toy.yumyum.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.Kind;
import toy.yumyum.repository.KindRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KindService {

	private final KindRepository kindRepository;

	@Transactional
	public Long saveKind(Kind kind) {
		validatedEmptyValueKind(kind);
		validatedDuplicatedKind(kind);

		kindRepository.save(kind);
		return kind.getId();
	}

	private void validatedEmptyValueKind(Kind kind) {
		if (kind.getName() == "" || kind.getName() == null) {
			throw new IllegalStateException("품종명을 입력해주세요.");
		}
	}

	private void validatedDuplicatedKind(Kind kind) {
		List<Kind> findKinds = kindRepository.findByName(kind.getName());

		if (!findKinds.isEmpty()) {
			throw new IllegalStateException("이미 등록된 품종입니다.");
		}
	}

	@Transactional
	public void deleteKind(Long id) {
		kindRepository.delete(id);
	}
}
