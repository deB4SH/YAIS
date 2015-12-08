package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

public class User implements Storable{

    public static String mongoDBident = "yais.user";

    private int id;
    private String username;
    private String password;
    private boolean userLoggedOn; //value just for the server

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.userLoggedOn = false;
    }

    public void store(MongoCollection collection) {
        Document doc = new Document("id", this.id)
                                .append("username", this.username)
                                .append("password", this.password);
        collection.insertOne(doc);
    }

    public static User restore(Document document){
        int id = Integer.parseInt(document.get("id").toString());
        String username = document.get("username").toString();
        String password = document.get("password").toString();

        return new User(id,username,password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUserLoggedOn() {
        return userLoggedOn;
    }

    public void setUserLoggedOn(boolean userLoggedOn) {
        this.userLoggedOn = userLoggedOn;
    }
}
