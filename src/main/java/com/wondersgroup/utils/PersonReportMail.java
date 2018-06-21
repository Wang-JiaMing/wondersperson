package com.wondersgroup.utils;

import java.util.List;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.emailModal
 * @authorName:wangjiaming
 * @createDate:2018-03-06
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class PersonReportMail {



    public static String getHead() {
        StringBuffer sbHead=new StringBuffer();
        sbHead.append("<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\"\n" +
                "      lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <title>人员系统Mini个人签到月报</title>\n" +
                "    <link rel=\"stylesheet\" href=\"http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css\"/>\n" +
                "    <script src=\"http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js\"></script>\n" +
                "    <script src=\"http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                "\n" +
                "</head>\n" +
                "<body>");
        return sbHead.toString();
    }

    /**
     *
     * @return
     */
    public static String contentJm(String... params) {
        StringBuffer sbContent=new StringBuffer();
        sbContent.append("\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"jumbotron\" style=\"background-color: #5a4780\">\n" +
                "        <h1 style=\"color: white\">人员系统Mini个人签到月报</h1>\n" +
                "        <br/><br/>\n" +
                "        <p style=\"color: white\" >\n" +
                "            签到规律性分析(个人)\n" +
                "        </p>\n" +
                "        <p style=\"color: white\"><span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\">上月获得"+params[0]+"次最早签到</span></p>\n" +
                "        <p style=\"color: white\"><span class=\"glyphicon glyphicon-dashboard\" aria-hidden=\"true\">签到最早时间"+params[1]+"</span></p>\n" +
                "        <p style=\"color: white\"><span class=\"glyphicon glyphicon-remove-sign\" aria-hidden=\"true\">签到最迟时间"+params[2]+"</span></p>\n" +
                "\n" +
                "        <br/><br/>\n" +
                "        <p style=\"color: white\" >\n" +
                "            签到规律性分析(公司)\n" +
                "        </p>\n" +
                "        <p style=\"color: white\"><span class=\"glyphicon glyphicon-grain\" aria-hidden=\"true\">上月最佳签到:"+params[3]+"("+params[4]+"次获得最早签到)</span></p>\n" +
                "        <p style=\"color: white\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\">上月由"+params[5]+"刷出最早签到时间"+params[6]+"</span></p>\n" +
                "\n" +
                "    </div>");
        return sbContent.toString();

    }

    public static String contentList(List<PersonReportList> personReportListList) {
        StringBuffer sbContent=new StringBuffer();
        sbContent.append("<div class=\"panel panel-default\">\n" +
                "        <!-- Default panel contents -->\n" +
                "        <div class=\"panel-heading\">签到表一览</div>\n" +
                "\n" +
                "        <!-- Table -->\n" +
                "        <table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>签到日期</th>\n" +
                "                <th>签到时间</th>\n" +
                "            </tr>\n" );

        for(PersonReportList personReport:personReportListList){
            sbContent.append( "<tr>\n" +
                              " <td>"+personReport.getServerDate()+"</td>\n" +
                              " <td>"+personReport.getRegisterDate()+"</td>\n" +
                              "</tr>\n" );
        }

        sbContent.append( "</table>\n" +
        "    </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "<script>\n" +
                "\n" +
                "</script>\n" +
                "</html>");
        return sbContent.toString();

    }

}
