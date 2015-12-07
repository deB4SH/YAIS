package de.b4sh.yais.net;

public enum MessageSubType {

    //USERMANAGEMENT
    USERLOGON("01"),
    USERLOGOFF("02"),
    USERREGISTER("03"),

    //DATAMANAGEMENT
    DATAROOM("01"),
    DATACABINET("02"),
    DATACABINETROW("03"),
    DATADOSSIER("04"),

    //PLACEHOLDER
    REQABCASDLKJASDLAS("01");


    private String value;

    MessageSubType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
