package de.b4sh.yais.misc;

public enum LogType {

    error("ERROR"),
    debug("DEBUG"),
    critial("CRITICAL"),
    out("OUT");

    private String val;
    LogType(String val){
        this.val = val;
    }

    public String getValue(){
        return "["+val+"]";
    }

}
