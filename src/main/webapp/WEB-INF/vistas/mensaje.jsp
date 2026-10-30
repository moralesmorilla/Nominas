<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/vistas/cabecera.jspf" %>

<c:set var="clase" value="${tipo == 'info' ? 'info' : 'error'}"/>
<c:set var="texto" value="${empty mensaje ? 'Se ha producido un error inesperado.' : mensaje}"/>

<h2>${clase == 'info' ? 'Información' : 'Error'}</h2>
<p class="mensaje ${clase}"><c:out value="${texto}"/></p>

<div class="acciones">
  <a class="btn secundario" href="javascript:history.back()">Volver</a>
  <a class="btn" href="${pageContext.request.contextPath}/index.html">Ir al inicio</a>
</div>

<%@ include file="/WEB-INF/vistas/pie.jspf" %>