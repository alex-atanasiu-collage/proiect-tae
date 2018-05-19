package com.ebuss.tae.ipieptea.tae.bean;

import java.io.*;

public class XSLTProviderImpl implements XSLTProvider {
    private String xslt = "";

    private static final String XSLT_FILE_NAME = "template.xslt";

    public XSLTProviderImpl() {
        String currentLine;

        BufferedReader reader = null;
        try {
            String path = new java.io.File( "." ).getCanonicalPath() + "\\src\\main\\resources\\xslt\\" + XSLT_FILE_NAME;

            reader = new BufferedReader(new FileReader(path));

            StringBuilder sb = new StringBuilder();
            while ((currentLine = reader.readLine()) != null) {
                sb.append(currentLine + "\n");
            }
            xslt = sb.toString();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getXSLT() {
        return xslt;
    }
}
