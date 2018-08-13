package com.json.validator;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by sampathkatari on 12/08/18.
 */
public class Main {
    public static void main( String[] args ) throws IOException, ProcessingException
    {
        Main main = new Main();
        boolean validJson = main.isValidJson("schema.json", "data.json");
        if(validJson) {
            System.out.println("Json is Valid according to schema");
        } else {
            System.out.println("Json is invalid according to schema");
        }
    }

    public boolean isValidJson(final String schemaFilePath, final String jsonFilePath) {
        try {
            ClassLoader loader = getClass().getClassLoader();
            File schemaFile = new File(loader.getResource(schemaFilePath).getFile());
            File jsonFile = new File(loader.getResource(jsonFilePath).getFile());
            return ValidationUtils.isJsonValid(schemaFile, jsonFile);
        }catch(Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean isValidJsonFromUrl(final String schemaUrl, final String jsonDataUrl) {
        try{
            URL schema = new URL(schemaUrl);
            URL jsonData = new URL(schemaUrl);
            return ValidationUtils.isJsonValid(schema, jsonData);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
}
