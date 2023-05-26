import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import play.Logger;
import play.Play;
import play.libs.IO;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testLoggerTheProcessingTime() {
        Response response = GET("/waiting?millis=500");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
        String str = getContent(response);
        Long time = Long.valueOf(str);
        assertTrue(500 < time);

        File log = Play.getFile("/logs/application.log");

        for (String line : IO.readLines(log)) {
            if (line.contains("[controllers.exam.ProcessingTime]")) {
                if (line.contains("[GET](127.0.0.1) /waiting millis=500, response time: ")) {
                    return;
                }
            }
        }
        assertTrue("not found ProcessingTime.", false);
    }

    @Test
    public void testCustomTagsHello() {
        Response response = GET("/hello");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
        assertContentEquals("Hello, world!!", response);
    }

}