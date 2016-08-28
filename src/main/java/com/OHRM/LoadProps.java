package com.OHRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Shohil on 28/08/2016.
 */
public class LoadProps {
    static Properties prop=null;
    static String propertiesFileLocation = "src/test/resources/";
    static String propFileName="TestData.properties";


    public static Properties getProperties() {
        return prop;
    }

    //getting values from property file
    public static String getProperty(String key)
    {
        if(getProperties()==null)
        {
            loadPropertyFile();
        }

        return prop.getProperty(key);
    }

    //Loading property file
    private static void loadPropertyFile() {
        prop = new Properties();
        FileInputStream input = null;
        try {
            if (System.getProperty("test_phase") == null || System.getProperty("test_phase").equals(""))
                //providing property file path as input
                input = new FileInputStream(propertiesFileLocation + propFileName);
            prop.load(input);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
