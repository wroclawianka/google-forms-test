package core;

import java.util.*;

/**
 * Created by dorot on 2017-03-29.
 */
public class DataHelper {

    public DataHelper() {
    }

    public Date daysFromNow(int value) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, value);
        return cal.getTime();
    }

    public String currentMonth() {
        Calendar cal = GregorianCalendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        List<String> monthNames = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        return monthNames.get(month);
    }
}
