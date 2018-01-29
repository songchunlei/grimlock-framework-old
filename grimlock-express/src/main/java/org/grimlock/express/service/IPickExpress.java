package org.grimlock.express.service;

import org.grimlock.express.entity.Express;

/**
 * 分拣快件
 * Created by songchunlei on 2018/1/22.
 */
public interface IPickExpress {
    boolean pick(Express[] src,int index);
}
