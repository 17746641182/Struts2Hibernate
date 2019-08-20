package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

    public static Date strToDate(String dateStr,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern); //设置日期格式
        return sdf.parse(dateStr);   //将String 类型的日期转化为java.util.Date
    }
}
