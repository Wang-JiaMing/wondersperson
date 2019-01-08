package com.wondersgroup.echartmodal;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName:yxjk_montior
 * @packageName:com.wonders.montior.echartModal
 * @authorName:wangjiaming
 * @createDate:2018-04-13
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class Pie implements Serializable {
    private List<String> legendData;
    private List<PieSeriesData> pieSeriesData;

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<PieSeriesData> getPieSeriesData() {
        return pieSeriesData;
    }

    public void setPieSeriesData(List<PieSeriesData> pieSeriesData) {
        this.pieSeriesData = pieSeriesData;
    }
}
