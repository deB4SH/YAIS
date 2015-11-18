package misc;

import java.util.ArrayList;

public class LogWriter {

    private static ArrayList<String> log;

    public LogWriter(){
        log = new ArrayList<String>();
    }

    public static void logToConsole(LogType logType ,String logMessage){
        log.add(logMessage);
        System.out.println(logType.getValue() +  " : " + logMessage);
    }

}
