package org.grimlock.express;

import org.grimlock.express.entity.Express;
import org.grimlock.express.enums.TimeLines;
import org.grimlock.express.enums.Value;

import java.util.Random;

/**
 * Created by songchunlei on 2018/1/22.
 */
public class MakeExpressArray {
    //数组长度
    public static final int ARRAY_LENGTH  = 4000;

    public static Express[] makeArray() {

        //new两个随机数发生器
        Random rSize = new Random();
        Random rTimeLine = new Random();
        Express[] result = new Express[ARRAY_LENGTH];
        for(int i=0;i<ARRAY_LENGTH;i++){
            //填充数组
            Express express = new Express(
                    rSize.nextBoolean() ? TimeLines.Normal : TimeLines.Fast,
                    rTimeLine.nextBoolean()? Value.Cheap: Value.Expensive);
            result[i] = express;
        }
        return result;
    }

    public static void main(String[] args) {
        Express[] expresses = makeArray();
        for(Express express : expresses){
            System.out.println(express);
        }
    }
}
