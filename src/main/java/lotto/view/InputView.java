package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.InputView.InputBonusNotNumericException;
import lotto.exception.InputView.InputLottoNotNumericException;
import lotto.exception.InputView.InputLottoSeparateRegexException;
import lotto.exception.InputView.InputPriceNotPositiveIntegerException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

    private static final String SPECIAL_CHARACTER_REGEX = ".*[!@#\\$%^&*()_+\\[\\]{};':\"\\\\<>./?`~\\-=|\\s].*";
    private static final String SEPARATOR_REGEX = ",";
    private static final String NUMERIC_REGEX = "^[0-9]*$";

    public int inputPrice() {
        String line = Console.readLine();

        validateInputPrice(line);

        return Integer.parseInt(line);
    }

    public List<Integer> inputLotto() {
        String line = Console.readLine();

        validateInputLottoSeparateRegex(line);
        validateInputLotto(line);

        String[] split = line.split(SEPARATOR_REGEX);
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }

    public int inputBonus() {
        String line = Console.readLine();

        validateInputBonus(line);

        return Integer.parseInt(line);
    }

    // 가격 입력값 검증
    private void validateInputPrice(String line) {
        if(!line.matches(NUMERIC_REGEX)) {
            throw new InputPriceNotPositiveIntegerException();
        }
    }

    // 로또 당첨 번호 입력값 검증
    private void validateInputLotto(String line) {
        String[] split = line.split(SEPARATOR_REGEX);
        for(String s : split) {
            if(!s.matches(NUMERIC_REGEX)) {
                throw new InputLottoNotNumericException(); //
            }
        }
    }

    private void validateInputLottoSeparateRegex(String line) {
        Pattern pattern = Pattern.compile(SPECIAL_CHARACTER_REGEX);
        Matcher matcher = pattern.matcher(line);

        if(matcher.find()) {
            throw new InputLottoSeparateRegexException();
        }
    }

    // 로또 당첨 보너스 번호 입력값 검증
    private void validateInputBonus(String line) {
        if(!line.matches(NUMERIC_REGEX)) {
            throw new InputBonusNotNumericException();
        }
    }
}
