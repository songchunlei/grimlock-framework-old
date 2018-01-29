package org.grimlock.express.entity;

import org.grimlock.express.enums.TimeLines;
import org.grimlock.express.enums.Value;

/**
 * 快件的实、
 * Created by songchunlei on 2018/1/22.
 */
public class Express {
    private final TimeLines timeLines;
    private final Value value;

    public Express(TimeLines timeLines, Value value) {
        this.timeLines = timeLines;
        this.value = value;
    }

    public TimeLines getTimeLines() {
        return timeLines;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "快件{" +
                "时速=" + timeLines +
                ", 价值=" + value +
                '}';
    }

}
