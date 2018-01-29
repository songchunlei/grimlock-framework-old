package org.grimlock.express.hasresult.impl;

import org.grimlock.express.entity.Express;
import org.grimlock.express.enums.TimeLines;
import org.grimlock.express.enums.Value;
import org.grimlock.express.service.IPickExpress;

/**
 * Created by songchunlei on 2018/1/22.
 */
public class PickCountImpl implements IPickExpress {
    @Override
    public boolean pick(Express[] src, int index) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //只挑快速，并且贵的
        Express express = src[index];
        if(express.getTimeLines().equals(TimeLines.Fast)
                && express.getValue().equals(Value.Expensive))
        {
            return true;
        }

        return false;
    }
}
