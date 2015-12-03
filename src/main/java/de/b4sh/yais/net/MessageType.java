package de.b4sh.yais.net;

public enum MessageType {

    //USERMANAGEMENT
    USER("01"),

    //DATAMANAGEMENT
    DATA("02"),

    //PLACEHOLDER
    REQABCASDLKJASDLAS("03");


    private String value;

    MessageType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
