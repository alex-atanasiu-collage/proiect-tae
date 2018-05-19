<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
<xsl:template match="person">
{
	name: <xsl:value-of select="name"/>
}

</xsl:template>
</xsl:stylesheet>