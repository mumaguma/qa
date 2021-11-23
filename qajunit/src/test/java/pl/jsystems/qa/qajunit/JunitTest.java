package pl.jsystems.qa.qajunit;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tags({@Tag("junit"), @Tag("smoke"), @Tag("noparam")})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("nazwa klasy która pojawi sie w raporcie")
public class JunitTest {

    private static final String STATYCZNY_STRING = "dkjahdkjahjha210982  123";

    @BeforeAll
    public static void beforeAll() {
        System.out.println("\nBefore all ********************************");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("\nAfter all ********************************");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("\nBefore each ********************************");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("\nAfter each ********************************");
    }

    @DisplayName("nazwa pierwszego testu")
    @Test
    public void firstTest() {

        final String STRING_TESTOWY = "asdgafdewaAO93";
        assertEquals(5,5,"aaa");
        assertNotEquals(2,5,"aaa");
        assertNull(null,"aaa");
        assertTrue(STRING_TESTOWY.contains("asd"));
        assertEquals(STRING_TESTOWY, "asdgafdewaAO93");
    }

    @Order(3)
    @Test
    public void stringTest() {
        String simpleString = "simpleString";
        String simpleString2 = "simpleString";
        String simpleString3 = new String("simpleString");
        String simpleString4 = new String("simpleString");
        assertEquals(simpleString, simpleString2, "pierwszy");
        assertSame(simpleString, simpleString2, "pierwszy same ");
        assertEquals(simpleString3, simpleString4, "drugi");
//        assertSame(simpleString3, simpleString4, "drugi same");
        assertTrue(simpleString == simpleString2, "== 1");
//        assertTrue(simpleString3 == simpleString4, "==2");
    }

    @Test
    public void doubleWrongTest(){
//        assertEquals(0.2*0.2, 0.04);  // błąd, bo double
        double result = new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue();
        assertEquals(result, 0.04);
    }

    @Order(2)
    @Test
    public void googleTruthTest() {
        assertThat(STATYCZNY_STRING).contains("hjha2");    // te nie maja message, ale za to są uniwersalne
//        assertThat(STATYCZNY_STRING).contains("hjha3");
    }

    @Order(1)
    @DisplayName("zadanie 1: czy string pasuje do wzorca")
    @Tags(@Tag("zadania"))
    @Test
    public void zadanie1Test() {
        // czy dwa stringi są takie same z wyjątkiem ...
        String resultString = "Wordpress powers 22% of the internet";
        String expectedString = "Wordpress powers [number]% of the internet";

        int expectedStart = expectedString.indexOf("[number]");
        String expectedStringBeginning = expectedString.substring(0, expectedStart);
        String expectedStringEnding = expectedString.substring(expectedStart+8, expectedString.length());

        resultString = resultString.replaceFirst(expectedStringBeginning, "");
        resultString = resultString.replaceFirst(expectedStringEnding, "");

        int procent = new Integer(resultString);

        assertTrue(procent >= 0, "Procent powinien być większy od 0");
        assertTrue(procent <= 100, "Procent powinien być większy od 0");

        // można było dać resultString.matches
    }

    @Test
    public void containsArrayTest() {
        List<Integer> result = Arrays.asList(1,2,3,4,5);
        List<Integer> expected = Arrays.asList(3,4,5);

        assertThat(result).containsAnyIn(expected);
//        assertThat(result).containsAnyOf(expected);
        assertThat(result).hasSize(5);
        assertThat(result).containsAnyOf(1,2,3);
    }

    @DisplayName("unitowy, sprawdźmy, czy jest exception oczekiwany")
    @Test
    public void gameplayExceptionTest() {
        GamePlay gamePlay = new GamePlay();
        Assertions.assertThrows(IllegalArgumentException.class, () -> gamePlay.play(0) );
    }

    @Test
    public void jsonTest() {
        String json = "{ \"Employees\": [ { \"userId\": \"krish\", \"jobTitle\": \"Developer\", \"firstName\": \"Krish\", \"lastName\": \"Lee\", \"employeeCode\": \"E1\", \"region\": \"CA\", \"phoneNumber\": \"123456\", \"emailAddress\": \"krish.lee@learningcontainer.com\" }, { \"userId\": \"devid\", \"jobTitle\": \"Developer\", \"firstName\": \"Devid\", \"lastName\": \"Rome\", \"employeeCode\": \"E2\", \"region\": \"CA\", \"phoneNumber\": \"1111111\", \"emailAddress\": \"devid.rome@learningcontainer.com\" }, { \"userId\": \"tin\", \"jobTitle\": \"Program Directory\", \"firstName\": \"tin\", \"lastName\": \"jonson\", \"employeeCode\": \"E3\", \"region\": \"CA\", \"phoneNumber\": \"2222222\", \"emailAddress\": \"tin.jonson@learningcontainer.com\" } ]}";
       
    }


}
