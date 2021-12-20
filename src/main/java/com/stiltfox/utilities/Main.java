package com.stiltfox.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder output = new StringBuilder();
        String outputFileName = "";

        if (args.length == 1)
        {
            File jsonFile = new File(args[0]);
            if (jsonFile.exists())
            {
                try
                {
                    output.append("<html><body><table>");
                    Map<String, Object> json = mapper.readValue(jsonFile, new TypeReference<Map<String,Object>>(){});

                    json.forEach((key, value) ->
                            output.append("<tr><td>").append(key).append("</td><td>").append(value).append("</td></tr>"));

                    output.append("</table></body></html>");
                    outputFileName = args[0].substring(0, args[0].lastIndexOf(".")) + ".html";
                    Files.writeString(Path.of(outputFileName), output.toString());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("This file does not exist");
            }
        }
        else
        {
            System.out.println("You must provide the file path to the json file to convert");
        }
    }
}
