package db;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoAPI {

    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection collection;

    public MongoAPI(String host, int port, String databaseName){
        this.mongoClient = new MongoClient(host,port);
        this.db = this.mongoClient.getDatabase(databaseName);

        this.createBase();


        getAllCollectionName();
    }

    private void createBase(){
        this.createCollection("yais.folder");
        this.createCollection("yais.shelf");

    }


    public void getAllCollectionName(){
        int collectionCounter = 0;
        for(String collectionName: this.db.listCollectionNames()){
            System.out.println("["+collectionCounter+"]: " +collectionName);
            collectionCounter++;
        }
    }

    public void createCollection(String collectionName){
        this.db.createCollection(collectionName);
    }

}
