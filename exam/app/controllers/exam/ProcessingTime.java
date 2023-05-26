package controllers.exam;

import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Finally;

/**
 * Provides logging of controller action processing times.
 * <pre>@With(LoggerTheProcessingTime.class)
public class Application extends Controller {
    public void index() {}
}
</pre>
 */
public class ProcessingTime extends Controller {

    static final ThreadLocal<Long> beginTimeMillis = new ThreadLocal<Long>();

    @Before(unless = "noAction")
    static void loggerRequestBefore() {
        beginTimeMillis.set(System.currentTimeMillis());
    }

    @Finally(unless = "noAction")
    static void loggerRequestFinally() {
        Long term = System.currentTimeMillis() - beginTimeMillis.get();
        Logger.info("[%s](%s) %s %s, response time: %dms", request.method, request.remoteAddress, request.path, request.querystring, term);
    }

}
