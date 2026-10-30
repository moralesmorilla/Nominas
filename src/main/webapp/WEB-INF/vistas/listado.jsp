<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/vistas/cabecera.jspf" %>

<h2>Listado de empleados</h2>

<c:choose>
  <c:when test="${empty empleados}">
    <p class="mensaje info">No hay empleados registrados en la base de datos.</p>
  </c:when>
  <c:otherwise>
    <table>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>DNI</th>
          <th>Sexo</th>
          <th>Categoría</th>
          <th>Años trabajados</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="e" items="${empleados}">
          <tr>
            <td><c:out value="${e.nombre}"/></td>
            <td><c:out value="${e.dni}"/></td>
            <td><c:out value="${e.sexo}"/></td>
            <td><c:out value="${e.categoria}"/></td>
            <td><c:out value="${e.anyos}"/></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <p class="resumen">Total: ${empleados.size()} empleado(s)</p>
  </c:otherwise>
</c:choose>

<div class="acciones">
  <a class="btn secundario" href="javascript:history.back()">Volver</a>
</div>

<%@ include file="/WEB-INF/vistas/pie.jspf" %>