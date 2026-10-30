<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/vistas/cabecera.jspf" %>

<h2>Consultar salario</h2>
<form class="formulario" action="${pageContext.request.contextPath}/salario" method="get">
  <label for="dni">DNI del empleado:</label>
  <input type="text" id="dni" name="dni" maxlength="9" required
         pattern="\d{8}[A-Za-z]" placeholder="32000032G" title="8 dígitos y una letra">
  <div class="acciones">
    <button type="submit" class="btn">Consultar</button>
    <a class="btn secundario" href="javascript:history.back()">Volver</a>
  </div>
</form>

<%@ include file="/WEB-INF/vistas/pie.jspf" %>