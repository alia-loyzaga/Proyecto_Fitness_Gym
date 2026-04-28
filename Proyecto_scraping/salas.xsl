<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" indent="yes"/>

<xsl:template match="/">
<salas>

<xsl:for-each select="//div[@class='tarjeta']">

<sala>
<nombreSala><xsl:value-of select="normalize-space(substring-after(p[1], 'Sala:'))"/></nombreSala>
<nombreActividad><xsl:value-of select="normalize-space(h3)"/></nombreActividad>
<entrenador><xsl:value-of select="normalize-space(substring-after(p[2], 'Entrenador:'))"/></entrenador>
<fechaHora>
<xsl:value-of select="normalize-space(substring-after(p[3], 'Fecha:'))"/>
<xsl:text> </xsl:text>
<xsl:value-of select="normalize-space(substring-after(p[4], 'Hora:'))"/>
</fechaHora>
<capacidadMaxima><xsl:value-of select="normalize-space(substring-after(p[5], 'Capacidad:'))"/></capacidadMaxima>
</sala>

</xsl:for-each>

</salas>
</xsl:template>

</xsl:stylesheet>