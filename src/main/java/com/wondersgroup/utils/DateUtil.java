package com.wondersgroup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.utils
 * @authorName:wangjiaming
 * @createDate:2018-02-26
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class DateUtil {
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

    public static Date parse(String str) throws Exception {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            local.set(sdf);
        }
        return sdf.parse(str);
    }

    public static String format(Date date) throws Exception {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            local.set(sdf);
        }
        return sdf.format(date);
    }
    public static String format(Date date,String format) throws Exception {
        SimpleDateFormat sdf = local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(format, Locale.US);
            local.set(sdf);
        }
        return sdf.format(date);
    }
}
