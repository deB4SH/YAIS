package de.b4sh.yais.misc;

import java.util.ArrayList;

public class LogWriter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static ArrayList<String> log;

    public LogWriter(){
        log = new ArrayList<String>();
    }

    public static void logToConsole(LogType logType ,String logMessage){
        log.add(logMessage);
        if(logType == LogType.error){
            System.out.println(ANSI_RED + "" +  logType.getValue() +  " : " + logMessage + "" + ANSI_RESET);
        }
        else if(logType == LogType.system){
            System.out.println(ANSI_GREEN + "" +  logType.getValue() +  " : " + logMessage + "" + ANSI_RESET);
        }
        else{
            System.out.println(logType.getValue() +  " : " + logMessage + "" + ANSI_RESET);
        }

    }

}
