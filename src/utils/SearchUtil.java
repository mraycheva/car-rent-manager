package utils;

public class SearchUtil {
    public boolean hasFilter(String s) {
        if (s.equals("")) {
            return false;
        }

        return true;
    }

    public boolean hasFilter(int id) {
        if (id < 1) {
            return false;
        }
        return true;
    }
}