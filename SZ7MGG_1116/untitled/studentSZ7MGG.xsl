<?xml version="1.0" encoding="UTF-8" ?>

<!--<xml-stylesheet type = "text/xsl" href="students_for-each.xsl"></xml-stylesheet>-->

<xsl:stylesheet version="1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

    <xsl:template match = "/">

        <html>
            <body>
                <h2>Hallgatok adatai - for-each, value-of</h2>

                <table border="4">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Vezeteknev</th>
                        <th>Keresztnev</th>
                        <th>Becenev</th>
                        <th>Kor</th>
                        <th>Osztondij</th>
                    </tr>

                    <!--for-each feldolgozasi utasitas. megkeres az XPath kifejezesnek ... -->

                    <xsl:for-each select="class/student">
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>

                            <td><xsl:value-of select="vezeteknev"/></td>
                            <td><xsl:value-of select="keresztnev"/></td>
                            <td><xsl:value-of select="becenev"/></td>
                            <td><xsl:value-of select="kor"/></td>
                            <td><xsl:value-of select="osztondij"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>