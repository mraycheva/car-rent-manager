package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDateUtil {

    public java.sql.Date formatStringToSQLDate(String strDate) throws Exception {
        // region Empty Date Field
        if (strDate.equals("")) {
            return null;
        }
        // endregion Empty Date Field

        SimpleDateFormat dfFormat1 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat dfFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = new Date();

        try {
            utilDate = dfFormat1.parse(strDate);
            long time = utilDate.getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            return sqlDate;
        } catch (Exception e) {
        }

        try {
            utilDate = dfFormat2.parse(strDate);
            long time = utilDate.getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            return sqlDate;
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // region Unparseable Date Field
        return null;
        // endregion Unparseable Date Field
    }
}
