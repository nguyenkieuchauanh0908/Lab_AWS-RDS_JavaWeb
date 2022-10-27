<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <link href="<%=request.getContextPath()%>/webjars/bootstrap/5.2.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Nhập các thông tin kết nối</h1>
    <div class="container">
        <form method="post" action="<%=request.getContextPath()%>/covid-info">
            <div class="mb-3 form-check">
                <label for="hostname" class="form-label">Host name</label>
                <input type="text" class="form-control" id="hostname" name="hostname" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3 form-check">
                <label for="port" class="form-label">Port</label>
                <input type="text" value="3306" class="form-control" id="port" name="port" aria-describedby="emailHelp" required readonly>
            </div>
            <div class="mb-3 form-check">
                <label for="type" class="form-label">Type</label>
                <select class="form-select" name="type" onchange="onSelectChange()" id="type" aria-describedby="emailHelp" required>
                    <option value="mysql">Aurora MySQL</option>
                    <option value="postgresql">PostgresSQL</option>
                </select>
            </div>
            <input type="hidden" name="drivername" id="drivername" value="com.mysql.jdbc.Driver"/>
            <div class="mb-3 form-check">
                <label for="type" class="form-label">User name</label>
                <input type="text" name="username" class="form-control" id="username" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3 form-check">
                <label for="type" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="password" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3 form-check">
                <label for="type" class="form-label">Database name</label>
                <input type="text" name="DBName" class="form-control" id="DBName" aria-describedby="emailHelp" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <a class="btn btn-outline-info" href="<%=request.getContextPath()%>/covid-info?dynamoDB=true">DynamoDB</a>
    </div>
<script>
    function onSelectChange(){
        let x = document.getElementById("type").value
        if(x === "mysql"){
            document.getElementById("port").value = "3306"
            document.getElementById("drivername").value = "com.mysql.jdbc.Driver"
        }else if (x === "postgresql"){
            document.getElementById("port").value = "5432"
            document.getElementById("drivername").value = "org.postgresql.Driver"
        }
    }
</script>
</body>
</html>