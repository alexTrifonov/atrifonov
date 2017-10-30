<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" />

    <xsl:template match="entries">
        <entries>
            <xsl:apply-templates/>
        </entries>
    </xsl:template>

    <xsl:template match="entry">
        <xsl:copy>
            <xsl:attribute name="field">
                <xsl:value-of select="field"/>
            </xsl:attribute>
        </xsl:copy>
    </xsl:template>

</xsl:stylesheet>