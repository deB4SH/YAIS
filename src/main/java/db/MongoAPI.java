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

    ArrayList<String> collections;

    public MongoAPI(String host, int port, String databaseName){
        this.mongoClient = new MongoClient(host,port);
        this.db = this.mongoClient.getDatabase(databaseName);

        this.collections = new ArrayList<String>();
        this.collections.add("yais.room");
        this.collections.add("yais.cabinet");
        this.collections.add("yais.cabinetrow");
        this.collections.add("yais.dossier");

        this.createBaseCollection();


        getAllCollectionName();
    }

    /**
     * Creates the basic collection for this application and checks if they exists
     */
    private void createBaseCollection(){



        this.testBaseCollection();
    }

    private void testBaseCollection(){

    }


    /**
     * Get all collections from mongoDB
     * @return List of all collections as ArrayList<String>
     */
    private ArrayList<String> getAllCollectionNameAsList(){
        ArrayList<String> args = new ArrayList<String>();
        for(String collectionName: this.db.listCollectionNames()){
            args.add(collectionName);
        }
        return args;
    }

    /**
     * Get all collections from mongoDB and prints them
     * @return void
     */
    public void getAllCollectionName(){
        int collectionCounter = 0;
        for(String collectionName: this.db.listCollectionNames()){
            System.out.println("["+collectionCounter+"]: " +collectionName);
            collectionCounter++;
        }
    }

    /**
     * Creates a collection in the mongoDB
     * @param collectionName
     */
    public void createCollection(String collectionName){
        this.db.createCollection(collectionName);
    }

}
