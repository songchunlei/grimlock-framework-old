package org.grimlock.express.hasresult;

import org.grimlock.express.MakeExpressArray;
import org.grimlock.express.entity.Express;
import org.grimlock.express.hasresult.impl.PickCountImpl;
import org.grimlock.express.service.IPickExpress;

/**
 * Created by songchunlei on 2018/1/22.
 */
public class OneThread {
    public static void main(String[] args) {

        int count = 0;
        Express[] src = MakeExpressArray.makeArray();//初始化一个快件的数组
        IPickExpress pickExpress = new PickCountImpl();
        long start = System.currentTimeMillis();
        for(int i= 0;i<src.length;i++){
            if (pickExpress.pick(src,i)) count++;
        }
        System.out.println("Plane transport count is "+count
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");

    }
}
