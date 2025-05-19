package utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
    private static Logger logger;

    private LoggerUtil() {} // Previeni istanziazione

    public static Logger getInstance() {
        if (logger == null) {
            logger = Logger.getLogger("BookManagerLogger");
            logger.setUseParentHandlers(false);
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);
        }
        return logger;
    }
}

