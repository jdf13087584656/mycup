<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>登录</title>
    <%--<link href="<%= request.getContextPath()%>/assets/css/customer.css" rel="stylesheet"/>--%>
    <%--<link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/jquery.mloading.css">--%>
    <%--<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>--%>
    <%--<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>--%>
    <%--<script type="text/javascript" src="<%= request.getContextPath()%>/libs/jquery-1.8.0.min.js"></script>--%>

</head>
<body class="bg1">
<form  class="form-signin" action="/login/form" method="post">
    <h2 class="form-signin-heading">用户登录</h2>
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"  class="form-control"  placeholder="请输入用户名"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"  class="form-control" placeholder="请输入密码" /></td>
        </tr>
        <tr>

            <td colspan="2">
                <button type="submit"  class="btn btn-lg btn-primary btn-block" >登录</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>