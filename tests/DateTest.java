import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void validate() {
        int day = 31;
        int month = 2;
        int year = 2001;

        IllegalArgumentException expectedOutput = new IllegalArgumentException("Invalid date");
    }
}