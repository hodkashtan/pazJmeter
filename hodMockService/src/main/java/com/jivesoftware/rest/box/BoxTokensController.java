package com.jivesoftware.rest.box;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gadi
 * Date: 8/28/13
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("spoTest")
public class BoxTokensController {

    private static String BOX_TOKENS_FILE_NAME = "spoTestTokens.json";

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getTokens")
    public String getTokens() throws IOException {

        FileInputStream fileReader = null;
        try {
            fileReader = new FileInputStream(getTestResource(BOX_TOKENS_FILE_NAME));
            String tokensJson = IOUtils.toString(fileReader, "utf-8");
            return tokensJson;
        }
        finally {
            if (fileReader != null)
                IOUtils.closeQuietly(fileReader);
        }
    }

    @POST
    @Path("/saveTokens")
    public void saveTokens(String tokens) throws IOException {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(getTestResource(BOX_TOKENS_FILE_NAME));
            fileWriter.write(tokens);
        } finally {
            if (fileWriter != null)
                IOUtils.closeQuietly(fileWriter);
        }
    }

    private String getTestResource(String resource) {

        return "/opt/dealroom/" + resource;
    }
}
