<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/vistas/cabecera.jspf" %>

<h2>Buscar empleados</h2>
<form class="formulario" action="${pageContext.request.contextPath}/buscar" method="get">
  <label for="criterio">Buscar por:</label>
  <select id="criterio" name="criterio" required>
    <option value="dni">DNI</option>
    <option value="nombre">Nombre</option>
    <option value="sexo">Sexo</option>
    <option value="categoria">Categoría</option>
    <option value="anyos">Años trabajados</option>
  </select>

  <label for="valor">Valor a buscar:</label>
  <input type="text" id="valor" name="valor" required>

  <div class="acciones">
    <button type="submit" class="btn">Buscar</button>
    <a class="btn secundario" href="javascript:history.back()">Volver</a>
  </div>
</form>

<%@ include file="/WEB-INF/vistas/pie.jspf" %>