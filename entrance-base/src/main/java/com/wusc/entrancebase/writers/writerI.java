package com.wusc.entrancebase.writers;

import com.wusc.entrancebase.model.inputers.Action;

public interface writerI {
    void write(Action action,StringBuilder buf);

}
