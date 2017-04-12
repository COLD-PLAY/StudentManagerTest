<%--
  Created by IntelliJ IDEA.
  User: coldplay
  Date: 17-4-11
  Time: 下午8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search student</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1 align="center">search student</h1>
    <div align="center">
        <form onsubmit="return checkall(this)" action="/StudentManagerSystem/HandleStudent" method="POST">
            <input type="hidden" name="handler" value="search">
            name: <input type="text" name="name">
            <br>
            sex: <input type="radio" name="sex" value="male">male
            <input type="radio" name="sex" value="female">female
            <br>
            stuid: <input type="text" name="id">
            <br>
            <input type="submit">
        </form>
    </div>

    <div style="position: absolute;">
        <a href="/index.jsp">back to index</a>
    </div>

    <script type="text/javascript">
        function checkall(f) {
            if (f.name.value == null || f.sex.value == null || f.stuid.value == null)
                return false;
            return true;
        }
    </script>
</body>
</html>
