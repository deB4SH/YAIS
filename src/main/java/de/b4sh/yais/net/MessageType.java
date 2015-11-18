package de.b4sh.yais.net;

public enum MessageType {

    // SIGN IN
    WELCOME("01-0001"),

    //USER AUTH
    REQUSERDATA("02-0001"),
    REQUSER("02-0002"),
    REQPW("02-0003"),

    //DB REQUEST
    REQABCASDLKJASDLAS("03-0001");


    private String value;

    MessageType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
