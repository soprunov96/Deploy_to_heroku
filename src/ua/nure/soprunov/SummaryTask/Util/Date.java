package ua.nure.soprunov.SummaryTask.Util;
import java.time.LocalDate;

/**
 * Get current date.
 *
 @authors Soprunov Igor
 *
 */

public class Date {
    LocalDate localDate;

    public LocalDate getLocalDate() {
        return localDate = LocalDate.now();
    }
}
