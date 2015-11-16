package db;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import mdl.Cabinet;
import mdl.CabinetRow;
import mdl.Dossier;
import mdl.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoAPI {

    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection collection;

    ArrayList<String> baseCollection;

    public MongoAPI(String host, int port, String databaseName){
        this.mongoClient = new MongoClient(host,port);
        this.db = this.mongoClient.getDatabase(databaseName);

        this.baseCollection = new ArrayList<String>();
        this.baseCollection.add(Room.mongoDBident);
        this.baseCollection.add(Cabinet.mongoDBident);
        this.baseCollection.add(CabinetRow.mongoDBident);
        this.baseCollection.add(Dossier.mongoDBident);

        this.createBaseCollection(baseCollection);

        getAllCollectionName();
    }

    /**
     * Creates collections from ArrayList and check if they exists
     * @param base
     */
    private void createBaseCollection(ArrayList<String> base) {
        ArrayList<String> args = this.getAllCollectionNameAsList();
        ArrayList<String> toCreate = new ArrayList<String>();
        for(String e: base){
            if(!args.contains(e)){
                toCreate.add(e);
            }
            else{
                System.out.println("contains already: " + e);
            }
        }
        //create missing base collections
        for(String e: toCreate){
            this.createCollection(e);
        }
        //test it every collection is there
        this.testBaseCollection(base);
    }

    /**
     * Creates the basic collection for this application and checks if they exists
     */
    private void createBaseCollectionBlank(ArrayList<String> base){
        for(String e: base){
            this.createCollection(e);
        }
        this.testBaseCollection(base);
    }

    private void testBaseCollection(ArrayList<String> base){
        ArrayList<String> args = this.getAllCollectionNameAsList();
        for(String e: base){
            if(!args.contains(e)){
                System.out.println("[ERROR]:[MONGODB]-[MONGODB DIDNT CREATE - " + e + "- CREATE IT ON YOUR OWN PLS]");
            }
        }
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
     * Get all collections from mongoDB and prints them (only yais related)
     * @return void
     */
    public void getAllCollectionName(){
        int collectionCounter = 0;
        for(String collectionName: this.db.listCollectionNames()){
            if(collectionName.contains("yais")){
                System.out.println("["+collectionCounter+"]: " +collectionName);
                collectionCounter++;
            }
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
