package com.wondersgroup.service;

import com.wondersgroup.dao.ChartRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.echartmodal.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.service
 * @authorName:wangjiaming
 * @createDate:2018-02-09
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class ChartService {

    @Autowired
    ChartRepoistory chartRepoistory;

    public Line getRegisterLine(String uid){
        Line line=new Line();
        line.setxAxisData(chartRepoistory.getXAxisDate(uid));
        List<String> legendstr=new ArrayList<>(1);
        legendstr.add("本人签到记录");
        line.setLegendData(legendstr);
        List<LineSeries> lineSeriesArrayList=new ArrayList<>();
        LineSeries lineSeries=new LineSeries();
        lineSeries.setName("本人签到记录");
        lineSeries.setSommth(true);
        lineSeries.setType("line");
        lineSeries.setData(chartRepoistory.getSeriesDate(uid));
        lineSeriesArrayList.add(lineSeries);
        line.setLineSeries(lineSeriesArrayList);
        return line;
    }
}
