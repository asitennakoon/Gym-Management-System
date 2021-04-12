import java.io.Serializable;

public class Date implements Serializable {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        validate(day, month, year);
    }

    public void validate(int day, int month, int year) {
        int maxDays;
        if (year < 1900 || year > 2020) {
            throw new IllegalArgumentException("Invalid date");
        } else {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    maxDays = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    maxDays = 30;
                    break;
                case 2:
                    if (((year % 4 == 0) &&
                            !(year % 100 == 0))
                            || (year % 400 == 0))
                        maxDays = 29;
                    else
                        maxDays = 28;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid date");
            }
            if (day > maxDays || day < 1) {
                throw new IllegalArgumentException("Invalid date");
            } else {
                this.day = day;
                this.month = month;
                this.year = year;
            }
        }

    }


    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
