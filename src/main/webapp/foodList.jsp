<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Ételek listája</title>
  <script>
    function getPortion(foodId) {
      fetch(`/api/foods/portion/${foodId}`)
              .then(response => {
                if (!response.ok) throw new Error("a kérés során hiba");
                return response.json();
              })
              .then(data => {
                alert(`Rendelt adag mennyisége: ${data}`);
              })
              .catch(error => {
                alert(" az adag lekérdezése közben hiba történt.");
              });
    }
  </script>
</head>
<body>
<h1>Ételek listája</h1>

<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>Étterem neve</th>
    <th>Étel neve</th>
    <th>Ár</th>
    <th>Művelet</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="food" items="${foodList}">
    <tr>
      <td>${food.id}</td>
      <td>${food.restaurantName}</td>
      <td>${food.foodName}</td>
      <td>${food.price}</td>
      <td>
        <button onclick="getPortion('${food.id}')">Rendelt adag</button>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
