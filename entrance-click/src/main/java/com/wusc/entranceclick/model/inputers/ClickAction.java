package com.wusc.entranceclick.model.inputers;

import com.wusc.entrancebase.model.inputers.Action;

public class ClickAction extends Action {
    private String preActionId;

    public String getPreActionId() {
        return preActionId;
    }

    public void setPreActionId(String preActionId) {
        this.preActionId = preActionId;
    }

    @Override
    public String toString() {
        return "ClickAction{" +
                "preActionId='" + preActionId + '\'' +
                '}';
    }
}
