package com.ebuss.tae.ipieptea.tae.bean;

public class XSLTProviderImpl implements XSLTProvider {
    @Override
    public String getXSLT() {
        return "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
                "<xsl:output method=\"xml\" indent=\"yes\" omit-xml-declaration=\"yes\" />" +
                "<xsl:template match=\"person\">" +
                "{\n" +
                "\tname: <xsl:value-of select=\"name\"/>\n" +
                "}\n" +
                "</xsl:template>\n" +
                "</xsl:stylesheet>";
    }
}
