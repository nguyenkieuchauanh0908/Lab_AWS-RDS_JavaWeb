<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<jsp:useBean id="covids" type="java.util.ArrayList<model.DataCovidViewModel>" scope="request" />
<html>
<head>
    <link href="<%=request.getContextPath()%>/webjars/bootstrap/5.2.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<c:if test="${error != null}">
    <h1 class="text-danger">LỖI KẾT NỐI!!!!</h1>
    <a class="btn btn-outline-info d-block w-25" href="<%=request.getContextPath()%>/home">Quay lại</a>
</c:if>
<c:if test="${error == null}">
<h2>Dữ liệu Covid-19</h2>

<select name="date" class="form-select w-25" id="date" onchange="onChangeLink()">
    <option value="01-01-2022" selected>01-01-2022</option>
    <option value="01-02-2022">01-02-2022</option>
    <option value="01-03-2022">01-03-2022</option>
    <option value="01-04-2022">01-04-2022</option>
    <option value="01-05-2022">01-05-2022</option>
</select>
<a id="link-submit" class="btn btn-outline-success w-25 d-block" href="<%=request.getContextPath()%>/covid-info?dynamoDB=true&date=01-01-2022">
    Xác nhận
</a>
<a class="btn btn-outline-info w-25 d-block" href="<%=request.getContextPath()%>/home">
    Back
</a>
<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>admin2</th>
        <th>province_state</th>
        <th>country_region</th>
        <th>last_update</th>
        <th>lat</th>
        <th>long_</th>
        <th>confirmed</th>
        <th>deaths</th>
        <th>recovered</th>
        <th>active</th>
        <th>combined_key</th>
        <th>incident_rate</th>
        <th>case_fatality_ratio</th>
        <th>date</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="i" items="${covids}">
            <tr>
                <td>${i.id}</td>
                <td>${i.admin2}</td>
                <td>${i.province_state}</td>
                <td>${i.country_region}</td>
                <td>${i.last_update}</td>
                <td>${i.lat}</td>
                <td>${i.long_}</td>
                <td>${i.confirmed}</td>
                <td>${i.deaths}</td>
                <td>${i.recovered}</td>
                <td>${i.active}</td>
                <td>${i.combined_key}</td>
                <td>${i.incident_rate}</td>
                <td>${i.case_fatality_ratio}</td>
                <td>${i.date}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</c:if>
<script>
    function onChangeLink(){
        <c:if test="${dynamoDB == null}">
            document.getElementById("link-submit").href="<%=request.getContextPath()%>/covid-info" + "?date=" + document.getElementById("date").value
        </c:if>
        <c:if test="${dynamoDB != null}">
            document.getElementById("link-submit").href="<%=request.getContextPath()%>/covid-info?dynamoDB=true" + "&date=" + document.getElementById("date").value
        </c:if>
    }
</script>
</body>
</html>
