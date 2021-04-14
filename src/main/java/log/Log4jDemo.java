package log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4jDemo {

    public static void main(String[] args) {
        Log log = LogFactory.getLog(CommonLogDemo.class);
        log.info("start...");
        log.warn("end.");
    }
}
