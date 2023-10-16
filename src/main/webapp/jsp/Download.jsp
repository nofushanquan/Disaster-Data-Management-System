<%--
  Created by IntelliJ IDEA.
  User: 12559
  Date: 2021/12/3
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="//api.map.baidu.com/api?type=webgl&v=1.0&ak=O6NBuPKhu8gBYAEldRu3wsrv2TkD4jbi"></script>
    <title>添加信息窗口</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMapGL.Map("allmap");
    var point = new BMapGL.Point(108.404, 34.915);
    map.centerAndZoom(point, 6);
    map.enableScrollWheelZoom(true);
    var marker = new BMapGL.Marker(point);  // 创建标注
    map.addOverlay(marker);              // 将标注添加到地图中
    var opts = {
        width : 200,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title : "故宫博物院" , // 信息窗口标题
        message:"这里是故宫"
    }
    var infoWindow = new BMapGL.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts);  // 创建信息窗口对象
    marker.addEventListener("click", function(){
        map.openInfoWindow(infoWindow, point); //开启信息窗口
    });



    var points = [new BMapGL.Point(109.19474,34.36847),
        new BMapGL.Point(109.19435,34.37712),
        new BMapGL.Point(109.19261,34.37617),
        new BMapGL.Point(109.19701,34.37728),
        new BMapGL.Point(109.19702,34.37776),new BMapGL.Point(109.19784,34.37738),
        new BMapGL.Point(109.19784,34.376911),new BMapGL.Point(109.1987,34.37333),
        new BMapGL.Point(109.19705,34.36824),new BMapGL.Point(109.1836,34.37393)];   //10个坐标点

    var marker1 =new BMapGL.Marker(points[1]);  // 创建10个标注
    var marker2 =new BMapGL.Marker(points[2]);
    var marker3 =new BMapGL.Marker(points[3]);
    var marker4 =new BMapGL.Marker(points[4]);
    var marker5 =new BMapGL.Marker(points[5]);
    var marker6 =new BMapGL.Marker(points[6]);
    var marker7 =new BMapGL.Marker(points[7]);
    var marker8 =new BMapGL.Marker(points[8]);
    var marker9 =new BMapGL.Marker(points[9]);
    var marker0 =new BMapGL.Marker(points[0]);

    map.addOverlay(marker1);              // 将标注添加到地图中
    map.addOverlay(marker2);
    map.addOverlay(marker3);
    map.addOverlay(marker4);
    map.addOverlay(marker5);
    map.addOverlay(marker6);
    map.addOverlay(marker7);
    map.addOverlay(marker8);
    map.addOverlay(marker9);
    map.addOverlay(marker0);

    //map.setViewport(points);         //调整地图的最佳视野为显示标注数组point

    var opts1 = {
        width : 200,     // 信息窗口宽度
        height: 100,
        title : '<span style="font-size:14px;color:#0A8021">DTU01</span>'
    };

    var infoWindow1 =new BMapGL.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>经度:</b1>09.19474</br><b>纬度:</b>34.36847</br><b>口碑：</b><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts1);  // 创建信息窗口对象，引号里可以书写任意的html语句。
    marker1.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[1]);});
    marker2.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[2]);});
    marker3.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[3]);});
    marker4.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[4]);});
    marker5.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[5]);});
    marker6.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[6]);});
    marker7.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[7]);});
    marker8.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[8]);});
    marker9.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[9]);});
    marker0.addEventListener("click", function(){map.openInfoWindow(infoWindow1,points[0]);});

</script>
