<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:param name="targetSkill"/>
    <xsl:template match="/">
        <html>
        <head>
            <title>All Recipes (XSLT View)</title>
            <style>
                body { font-family: Arial, sans-serif; padding: 20px; }
                table { border-collapse: collapse; width: 100%; margin-top: 20px; }
                th, td { border: 1px solid #333; padding: 10px; text-align: left; }
                th { background-color: #f4f4f4; }
                /* The required CSS classes for the task */
                .match { background-color: yellow; }
                .nomatch { background-color: lightgreen; }
            </style>
        </head>
        <body>
            <h2>All Recipes (XSLT Generated)</h2>
            <p>Highlighting recipes that match the first user's skill level: <strong><xsl:value-of select="$targetSkill"/></strong></p>
            
            <table>
                <tr>
                    <th>Title</th>
                    <th>Cuisines</th>
                    <th>Difficulty</th>
                </tr>
                
                <xsl:for-each select="//recipe">
                    
                    <xsl:variable name="rowClass">
                        <xsl:choose>
                            <xsl:when test="normalize-space(difficulty) = $targetSkill">match</xsl:when>
                            <xsl:otherwise>nomatch</xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>

                    <tr class="{$rowClass}">
                        <td><xsl:value-of select="title"/></td>
                        <td>
                            <xsl:for-each select="cuisines/cuisine">
                                <xsl:value-of select="."/>
                                <xsl:if test="position() != last()">, </xsl:if>
                            </xsl:for-each>
                        </td>
                        <td><xsl:value-of select="difficulty"/></td>
                    </tr>
                </xsl:for-each>
            </table>
            <br/>
            <a href="/recipes">Back to Home</a>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>