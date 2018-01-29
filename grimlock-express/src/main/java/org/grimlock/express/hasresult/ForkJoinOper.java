package org.grimlock.express.hasresult;

import org.grimlock.express.MakeExpressArray;
import org.grimlock.express.entity.Express;
import org.grimlock.express.hasresult.impl.PickCountImpl;
import org.grimlock.express.service.IPickExpress;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 计数任务一分为2
 * Created by songchunlei on 2018/1/22.
 */
public class ForkJoinOper {

    private static class CountTask extends RecursiveTask<Integer>{

        private final static int Threadhold = 10; //数组大小10个以上就分，10个以下就操作业务
        private Express[] src;
        private int fromIndex;//任务开始
        private int endIndex;//任务结束
        private IPickExpress pickExpress;

        public CountTask(Express[] src, int fromIndex, int endIndex, IPickExpress pickExpress) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.endIndex = endIndex;
            this.pickExpress = pickExpress;
        }

        @Override
        protected Integer compute() {

            if(endIndex-fromIndex<Threadhold)
            {
                //10个以下操作业务
                int count = 0;
                for (int i=fromIndex;i<endIndex;i++)
                {
                    if(pickExpress.pick(src,i))
                    {
                        count++;
                    }

                }
                return count;
            }
            else
            {
                //10个以上分两个小任务
                int midIndex = (fromIndex + endIndex)/2;
                CountTask left = new CountTask(src,fromIndex,midIndex,pickExpress);
                CountTask right = new CountTask(src,midIndex,endIndex,pickExpress);
                invokeAll(left,right);
                return left.join()+right.join();
            }


        }

    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Express[] src = MakeExpressArray.makeArray();
        IPickExpress pickExpress = new PickCountImpl();
        long start = System.currentTimeMillis();
        CountTask total = new CountTask(src,0,src.length-1,pickExpress);
        System.out.println("Plane transport count is "+total.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
