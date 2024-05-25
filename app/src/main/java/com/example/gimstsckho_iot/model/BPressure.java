package com.example.gimstsckho_iot.model;

import android.content.Intent;

public class BPressure {
    private Integer sbp;
    private  Integer dsp;

    public BPressure() {
    }

    public BPressure(Integer sbp, Integer dsp) {
        this.sbp = sbp;
        this.dsp = dsp;
    }

    public Integer getSbp() {
        return sbp;
    }

    public void setSbp(Integer sbp) {
        this.sbp = sbp;
    }

    public Integer getDsp() {
        return dsp;
    }

    public void setDsp(Integer dsp) {
        this.dsp = dsp;
    }
}
