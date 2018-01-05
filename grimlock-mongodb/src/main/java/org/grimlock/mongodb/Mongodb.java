package org.grimlock.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
/*import com.mongodb.client.model.Filters;*/
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.math.BigDecimal;
import java.util.*;

//使用静态导入，这样可以经常用Filters的方法
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 * mongodb原生s
 * Created by songchunlei on 2018/1/4.
 */
public class Mongodb {

    private MongoDatabase db;
    private MongoCollection<Document> doc;
    private MongoClient client;

    public void init(){
        client = new MongoClient("192.",27022);
        db = client.getDatabase("scl");
        doc = db.getCollection("users");
    }

    public void insertDemo()
    {
        Document doc1 = new Document();
        doc1.append("username","scl");
        doc1.append("country","USA");
        doc1.append("age",20);
        doc1.append("length",1.76f);
        doc1.append("salary",new BigDecimal("6555.3"));


        Map<String,String> address = new HashMap<String,String>();
        address.put("code","0000");
        address.put("add","xxx0000");
        doc1.append("address",address);

        Map<String,Object> favorites = new HashMap<String,Object>();
        favorites.put("movies",Arrays.asList("007","008"));
        favorites.put("cities", Arrays.asList("hangzhou","shenzhou"));
        doc1.append("favorites",favorites);

        //写入
        doc.insertMany(Arrays.asList(doc1));


    }


    public void DeleteDemo(){
        //doc.deleteMany(Filters.eq("username","scl"));
        //等于条件删除
        doc.deleteMany(eq("username","scl"));
        //and条件删除
        doc.deleteMany(and(gt("age",8),lt("age",25)));
    }

    public void UpdateDemo()
    {
        //更新username=scl，加$set表示只改age，替换
        UpdateResult result = doc.updateMany(eq("username","scl"),
                new Document("$set",new Document("age",6))
        );
        result.getModifiedCount();

        //复杂更新favorites里的cities，增加
        UpdateResult result2 = doc.updateMany(eq("favorites.cities","hangzhou"),
            addEachToSet("favorites.movies",Arrays.asList("009","1000"))
        );

    }

    public void FindDemo(){

        final List<Document> ret = new ArrayList<>();
        Block<Document> printBlock =  new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
                ret.add(document);
            }
        };

        //正常查询
        FindIterable<Document> find  = doc.find(all("favorites.cities",Arrays.asList("hangzhou","shenzhou")));
        find.forEach(printBlock);
        System.out.println(ret.size());

        //正则查询
        String regexStr = ".*s.*";
        Bson regex = regex("usrname",regexStr);
        Bson or =or(eq("country","hangzhou"),eq("country","USA"));
        FindIterable<Document> find2 = doc.find(and(regex,or));
        find2.forEach(printBlock);
        System.out.println(ret.size());
    }

}
