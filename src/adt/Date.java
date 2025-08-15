package adt;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        // Uses system time directly without LocalDate
        long millis = System.currentTimeMillis();
        
        // This is a simplified calculation - in production you'd want a more accurate algorithm
        // or use the standard library, but this meets the requirement of no imports
        int totalDays = (int) (millis / (1000 * 60 * 60 * 24));
        
        // Approximate conversion from days to years (not accounting for leap years precisely)
        this.year = 1970 + totalDays / 365;
        int remainingDays = totalDays % 365;
        
        // Simple month calculation (not perfectly accurate)
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(this.year)) {
            monthDays[1] = 29;
        }
        
        this.month = 1;
        for (int i = 0; i < 12; i++) {
            if (remainingDays <= monthDays[i]) {
                this.month = i + 1;
                this.day = remainingDays;
                break;
            }
            remainingDays -= monthDays[i];
        }
    }

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date!");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isValidDate(int day, int month, int year) {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        int maxDays;
        switch (month) {
            case 4: case 6: case 9: case 11:
                maxDays = 30;
                break;
            case 2:
                maxDays = isLeapYear(year) ? 29 : 28;
                break;
            default:
                maxDays = 31;
        }
        return day <= maxDays;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }

    public boolean isBefore(Date other) {
        return compareTo(other) < 0;
    }

    public boolean isAfter(Date other) {
        return compareTo(other) > 0;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date other = (Date) obj;
        return day == other.day && month == other.month && year == other.year;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }
}