<%--
  Created by IntelliJ IDEA.
  User: 12559
  Date: 2021-03-24
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.disastercode" %>
<%@ page import="entity.disasterinfo" %>
<%@ page import="java.sql.Timestamp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>灾情数据管理系统</title>
    <!-- daterange picker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href=".${pageContext.request.contextPath}/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <%
        String errorInfo = (String)request.getAttribute("error");         // 获取错误属性
        if(errorInfo != null) {
    %>
    <script type="text/javascript" language="javascript">
        alert("<%=errorInfo%>");                                            // 弹出错误信息
    </script>
    <%
        }
    %>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-bell"></i>
                    <span class="badge badge-warning navbar-badge">15</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-item dropdown-header">15 Notifications</span>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-envelope mr-2"></i> 4 new messages
                        <span class="float-right text-muted text-sm">3 mins</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-users mr-2"></i> 8 friend requests
                        <span class="float-right text-muted text-sm">12 hours</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-file mr-2"></i> 3 new reports
                        <span class="float-right text-muted text-sm">2 days</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                </div>
            </li>
        </ul>
    </nav>
    <%
        List<disasterinfo> datas = (List<disasterinfo>) request.getAttribute("datas");
        request.setAttribute("datas",datas);
        Timestamp ts=(Timestamp)request.getAttribute("ts");
        request.setAttribute("ts",ts);
        Timestamp te=(Timestamp)request.getAttribute("te");
        request.setAttribute("te",te);
    %>
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user (optional) -->

            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <p class="d-block"><font color="#f0ffff">2021140789</font></p>
                </div>
            </div>
            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/datasearchServlet" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                查询数据
                                <span class="right badge badge-danger">New</span>
                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/jsp/Upload.jsp" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                上传数据
                                <span class="right badge badge-danger">New</span>
                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/mapServlet" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                地图
                                <span class="right badge badge-danger">New</span>
                            </p>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>灾情数据信息</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>
        <!-- Main content -->

        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <div class="card card-danger">
                            <div class="card-header">
                                <h3 class="card-title">数据筛选</h3>
                            </div>
                            <form action="${pageContext.request.contextPath}/dataselectServlet" method="post">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="sdate">起始时间</label>
                                        <input class="form-control" type="datetime-local" id="sdate" name="sdate" />
                                    </div>
                                    <div class="form-group">
                                        <label for="edate">结束时间</label>
                                        <input class="form-control" type="datetime-local" id="edate" name="edate" />
                                    </div>
                                    <!--div class="form-group">
                                        <label for="grade">震级</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="grade" name="grade">
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="country">国家</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="country" name="country">
                                                <option></option>
                                            </select>
                                        </div>
                                    </div-->
                                    <!-- /.form group -->
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary">搜索</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card card-primary">
                            <div class="card-header">
                                <h3 class="card-title">下载数据</h3>
                            </div>
                            <form action="${pageContext.request.contextPath}/DownloadServlet?ts=<%=ts%>&te=<%=te%>" method="post">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="kind">格式</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="kind" name="kind">
                                                <option>JSON</option>
                                                <option>excel</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- /.form group -->
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary">下载</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>震级</th>
                                        <th>时间</th>
                                        <th>纬度</th>
                                        <th>经度</th>
                                        <th>等级</th>
                                        <th>位置</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${datas}" var="data">
                                        <tr>
                                            <td>
                                                <a class="btn btn-warning bt-sm" href="${pageContext.request.contextPath}/datadetailServlet?id=${data.dataid}">
                                                    <i class="fa fa-fw fa-pencil"></i>
                                                        ${data.dataid}
                                                </a>
                                            </td>
                                            <td>${data.magnitude}</td>
                                            <td>${data.created}</td>
                                            <td>${data.latitude}</td>
                                            <td>${data.longitude}</td>
                                            <td>${data.grade}</td>
                                            <td>${data.location}</td>
                                            <td>
                                                <a class="btn btn-warning bt-sm" href="${pageContext.request.contextPath}/changedataServlet?id=${data.dataid}">
                                                    <i class="fa fa-fw fa-pencil"></i>
                                                    修改
                                                </a>
                                                <a class="btn btn-danger bt-sm" style="margin-left: 20px;" href="${pageContext.request.contextPath}/deletedataServlet?id=${data.dataid}&ts=<%=ts%>&te=<%=te%>">
                                                    <i class="fa fa-fw fa-minus"></i>
                                                    删除
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.card-body -->
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="float-right d-none d-sm-block">
            <b>基于机器学习的运动医学影像信息算法的研究与仿真</b>
        </div>
    </footer>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables  & Plugins -->
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jszip/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/pdfmake/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/pdfmake/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/moment/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/moment/locale/zh-cn.js"></script>
<script src="${pageContext.request.contextPath}/plugins/inputmask/jquery.inputmask.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
<!-- date-range-picker -->
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>

<script>
    $(function () {
        $("#example1").DataTable({
            "responsive": true, "lengthChange": false, "autoWidth": false,
            "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
        }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
        $('#example2').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false,
            "responsive": true,
        });
        bsCustomFileInput.init();
    });
</script>
</body>
</html>
