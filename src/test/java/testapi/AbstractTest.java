package testapi;

import io.restassured.internal.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AbstractTest {

    protected String getResourceAsString(String resource) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(resource);
     //   byte[] bytes = inputStream.readAllBytes();

        byte[] bytes = IOUtils.toByteArray(inputStream);
        return new String(bytes);
    }

    protected File getFile(String resource) throws IOException {
        return new File(getClass().getResource(resource).getFile());
    }

}
