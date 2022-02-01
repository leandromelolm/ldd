<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:key name="subordinates" match="employee" use="reportsTo" />  
    <xsl:template match="employees">
        <html>
            <head>
                <title>
                    <xsl:text>Q2.xsl</xsl:text>
                </title>
            </head>
            <body>
                <h1>
                    <xsl:text>Employees</xsl:text>
                </h1>
                <hr/>       
                <xsl:apply-templates
                    select="//employee[generate-id()=generate-id(key('subordinates', reportsTo)[1])]"/>
            </body>
        </html>
    </xsl:template>    
    <xsl:template match="employee">           
        <div>
            <xsl:value-of select="reportsTo"/>           
        </div> 
        <ul>
            <xsl:for-each select="key('subordinates',reportsTo)">            
                <li> 
                    <xsl:value-of select="concat(firstName, ' ' ,lastName)"/>  
                </li>     
            </xsl:for-each>  
        </ul>
    </xsl:template>                
</xsl:stylesheet>

<!--
    Usando XSLT Netbeans 12.3
    Executar XSL Transformation 
    1. XSL Transformation ( Atalho - botão direito) 
    2. XML Source 
    3. Browse 
    4. PROCURAR O XML 
    5. Output "Dar nome ao arquivo de saída exemplo.html"
-->