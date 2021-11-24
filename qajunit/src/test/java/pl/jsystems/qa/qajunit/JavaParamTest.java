package pl.jsystems.qa.qajunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.google.common.truth.Truth.assertThat;

@Tags({@Tag("junit"), @Tag("smoke"), @Tag("param")})
@DisplayName("Param test")
public class JavaParamTest {

    @DisplayName("Modulo 5 check")
    @ParameterizedTest(name = "Parameter test with value {0}")
    @ValueSource(ints = {5,15,25})
    @Tags(@Tag("param"))
    public void firstParamTest(int number) {
        Assertions.assertEquals(number %5, 0 );
    }

    @DisplayName("String check")
    @ParameterizedTest(name = "Parameter test with value {0}")
    @ValueSource(strings = {"Hello","Hello junit", "Hello students"})
    @Tags(@Tag("string"))
    public void stringParamTest(String text) {
        assertThat(text.contains("Hello"));
        assertThat(text).contains("Hello");
        assertThat(text).startsWith("Hello");
    }

    @DisplayName("Parameter test with multi param")
    @ParameterizedTest(name = "Parameter test with value {0}, {1}")
//    @CsvSource(value = {"Hello, 5","Hello junit, 10", "Hello students, 25"})
    @CsvSource(value = {"Hello, world; 5","Hello junit; 10", "Hello students; 25"}, delimiter = ';')
//    @CsvSource(value = {"'Hello, world'; 5","Hello junit; 10", "Hello students, 25"})
    @Tags(@Tag("csv"))
    public void multiParamTest(String text, int number) {
        assertThat(text).startsWith("Hello");
        Assertions.assertEquals(number %5, 0 );
    }

    @DisplayName("Parameter test with multi param from file")
    @ParameterizedTest(name = "Parameter test with value {0}, {1}")
    @CsvFileSource(resources = "/plik.csv")
//    @CsvFileSource(resources = "/plik.csv", delimiter = ";")
    @Tags(@Tag("csv"))
    public void multiFileParamTest(String text, int number) {
        assertThat(text).startsWith("Hello");
        Assertions.assertEquals(number %5, 0 );
    }

    @DisplayName("Parameter enum")
    @ParameterizedTest(name = "Enum test {0}")
    @EnumSource(value = ParamEnum.class)
    @Tags(@Tag("enum"))
    public void enumTest(ParamEnum param) {
        assertThat(param.toString().contains("ENUM"));

    }

    enum ParamEnum {
        ENUM_ONE,
        ENUM_TWO
    }


}
