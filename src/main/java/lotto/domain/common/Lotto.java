package lotto.domain.common;


import lotto.exception.InputView.InputLottoNotNumericException;
import lotto.exception.Lotto.LottoDuplicationException;
import lotto.exception.Lotto.LottoNotInRangeException;
import lotto.exception.Lotto.LottoNotSizeSixException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public record Lotto(List<Integer> numbers) {

    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_SIZE = 6;
    private static final String NUMERIC_REGEX = "^[0-9]*$";


    public Lotto {
        validateLottoSize(numbers);
        validateLottoNumeric(numbers);
        validateLottoRange(numbers);
        validateLottoDuplication(numbers);
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoNotSizeSixException();
        }
    }

    private void validateLottoNumeric(List<Integer> numbers) {
        if (!numbers.stream().map(i -> String.valueOf(i)).allMatch(s -> s.matches(NUMERIC_REGEX))) {
            throw new InputLottoNotNumericException();
        }
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (int i : numbers) {
            if (i < LOTTO_MIN_NUMBER || i > LOTTO_MAX_NUMBER) {
                throw new LottoNotInRangeException();
            }
        }
    }

    private void validateLottoDuplication(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (Integer number : numbers) {
            if (!uniqueNumbers.add(number)) {
                throw new LottoDuplicationException();
            }
        }
    }
}