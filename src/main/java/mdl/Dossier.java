package mdl;

import java.util.Date;

public class Dossier {

    public static String mongoDBident = "yais.dossier";

    //references
    private int id;

    //dossier specific data
    private String name;
    private String archiveObject;
    private Date lastUse;
    private Date createdOn;

}
