package by.epam.spring.hometask.view.util;

import org.apache.log4j.Logger;

import java.util.List;

public class PrinterHelper {

    private static final Logger log = Logger.getLogger(PrinterHelper.class);

    public static void printMessageInBox(List<String> list) {

        int maxStringLength = 0;
        for (String s : list) {
            int length = s.length();
            if (length > maxStringLength) {
                maxStringLength = length;
            }
        }
        log.info("\n+");
        for (int i = 0; i < maxStringLength + 4; i++) {
            log.info("-");
        }
        log.info("+\n");
        for (String s : list) {
            log.info("|  " + s);
            int length = s.length();
            for (int i = 0; i < maxStringLength + 2 - length; i++) {
                log.info(" ");
            }
            log.info("|\n");
        }
        log.info("+");
        for (int i = 0; i < maxStringLength + 4; i++) {
            log.info("-");
        }
        log.info("+\n");
    }
}
