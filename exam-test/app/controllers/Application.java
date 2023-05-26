package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(controllers.exam.ProcessingTime.class)
public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void hello() {
        render();
    }

    public static void waiting(long millis) throws InterruptedException {
        long now = System.currentTimeMillis();
        Thread.sleep(millis);
        renderArgs.put("procTime", System.currentTimeMillis() - now);
        render();
    }
}