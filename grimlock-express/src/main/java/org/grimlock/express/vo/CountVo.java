package org.grimlock.express.vo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 快件记数
 * Created by songchunlei on 2018/1/22.
 */
public class CountVo {

    /*飞机运输-高价值的快件数量*/
    private AtomicInteger plane = new AtomicInteger(0);

    /*高铁运输-高价值的普件数量*/
    private AtomicInteger highSpeedRail= new AtomicInteger(0);

    /*火车运输-低价值的快件数量*/
    private AtomicInteger normalRail= new AtomicInteger(0);

    /*货车运输-低价值的普件数量*/
    private AtomicInteger trunk= new AtomicInteger(0);

    public AtomicInteger getPlane() {
        return plane;
    }

    public void setPlane(AtomicInteger plane) {
        this.plane = plane;
    }

    public AtomicInteger getHighSpeedRail() {
        return highSpeedRail;
    }

    public void setHighSpeedRail(AtomicInteger highSpeedRail) {
        this.highSpeedRail = highSpeedRail;
    }

    public AtomicInteger getNormalRail() {
        return normalRail;
    }

    public void setNormalRail(AtomicInteger normalRail) {
        this.normalRail = normalRail;
    }

    public AtomicInteger getTrunk() {
        return trunk;
    }

    public void setTrunk(AtomicInteger trunk) {
        this.trunk = trunk;
    }

    @Override
    public String toString() {
        return "计数{" +
                "plane=" + plane +
                ", highSpeedRail=" + highSpeedRail +
                ", normalRail=" + normalRail +
                ", trunk=" + trunk +
                '}';
    }
}
