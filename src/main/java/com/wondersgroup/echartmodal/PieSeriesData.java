package com.wondersgroup.echartmodal;

import java.io.Serializable;

/**
 * @projectName:yxjk_montior
 * @packageName:com.wonders.montior.echartModal
 * @authorName:wangjiaming
 * @createDate:2018-04-13
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class PieSeriesData implements Serializable {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
