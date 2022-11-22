<?xml version="1.0" encoding="UTF-8" ?>

<!--<xml-stylesheet type = "text/xsl" href="students_for-each.xsl"></xml-stylesheet>-->

<xsl:stylesheet version="1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

    <xsl:template match = "/">

        <html>
            <style>
                table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
                }

                td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
                }

                tr:nth-child(even) {
                background-color: #dddddd;
                }

                caption {
                display: table-caption;
                text-align: center;
                }

            </style>
            <body>
                <h2>Orarend for-each, value-of</h2>

                <table border="8">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Tipus</th>
                        <th>Tárgy</th>
                        <th>Időpont</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                    </tr>

                    <!--for-each feldolgozasi utasitas. megkeres az XPath kifejezesnek ... -->

                    <xsl:for-each select="DemjanCsongor_orared/ora">
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>
                            <td>
                                <xsl:value-of select="@tipus"/>
                            </td>

                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="idopont"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>