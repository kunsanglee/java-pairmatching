package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CourseTest {

    @DisplayName("입력한 과정이 존재하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"백엔드", "프론트엔드"})
    void validateCourseSuccess(String course) {
        Assertions.assertThatCode(() -> Course.of(course))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력한 과정이 존재하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"없는거", "가나다", "123", "", " ", "ㅁㄴㅇ", "asd"})
    void validateCourseFail(String course) {
        Assertions.assertThatCode(() -> Course.of(course))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
