<%--
  Created by IntelliJ IDEA.
  User: 12559
  Date: 2021/1/14
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>灾情数据管理系统</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">

</head>
<body class="hold-transition login-page">
  <div class="login-box">
  <!-- /.login-logo -->
      <form action="${pageContext.request.contextPath}/datasearchServlet" method="post">
        <button type="submit" class="btn btn-primary">查询数据</button>
      </form>
      <!--a href="${pageContext.request.contextPath}/jsp/Download.jsp" class="nav-link">
        <i class="nav-icon fas fa-th"></i>
          <p>
            test
            <span class="right badge badge-danger">New</span>
          </p>
      </a-->
    <!-- /.login-card-body -->
  </div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
</body>
</html>
