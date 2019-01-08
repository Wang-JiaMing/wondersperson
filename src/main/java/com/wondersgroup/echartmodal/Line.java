package com.wondersgroup.echartmodal;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName:yxjk_montior
 * @packageName:com.wonders.montior.modal
 * @authorName:wangjiaming
 * @createDate:2018-04-11
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class Line implements Serializable{
    /**
     * X轴
     */
    public List<String> xAxisData;
    /**
     * 图例
     */
    public List<String> legendData;

    public List<LineSeries> lineSeries;

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<LineSeries> getLineSeries() {
        return lineSeries;
    }

    public void setLineSeries(List<LineSeries> lineSeries) {
        this.lineSeries = lineSeries;
    }
}
