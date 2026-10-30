<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/vistas/cabecera.jspf" %>

<h2>Salario del empleado</h2>
<table class="tabla-detalle">
  <tr><th>DNI</th><td><c:out value="${dni}"/></td></tr>
  <tr><th>Sueldo</th><td><c:out value="${sueldo}"/> €</td></tr>
</table>

<div class="acciones">
  <a class="btn secundario" href="javascript:history.back()">Volver</a>
</div>

<%@ include file="/WEB-INF/vistas/pie.jspf" %>