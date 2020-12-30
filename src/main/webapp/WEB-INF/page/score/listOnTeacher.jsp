<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>专业管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div style="margin: 10px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">

                    <div class="layui-inline">
                        <label class="layui-form-label">班级</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courseName" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-primary"  lay-submit lay-filter="search-btn"><i class="layui-icon"></i> 搜 索</button>
                    </div>
                </div>
            </form>
        </div>
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="save">
                    <i class="fa fa-pencil"></i>
                    评分
                </button>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
            table.render({
                elem: '#currentTableId',
                url: '${basePath}score/queryForTeacher',
                contentType:'application/json',
                method:"post",
                where: {teacherId: ${user.id}},
                toolbar: '#toolbar',
                defaultToolbar: ['filter', 'exports', 'print'],
                page: true,
                cols: [[
                    {type: "checkbox", width: 50},
                    {field: 'year', width: 80, title: '年度',templet:'<div>{{d.section.year}}</div>'},
                    {field: 'type',  title: '类型',templet:'<div>{{d.section.type}}</div>'},
                    {field: 'clazzName', title: '所属班级',templet:'<div>{{d.clazz.clazzName}}</div>'},
                    {field: 'courseName', title: '课程',templet:'<div>{{d.course.courseName}}</div>'},
                    {field: 'stuName', title: '学生姓名',templet:'<div>{{d.student.stuName}}</div>'},
                    {field: 'score',  title: '成绩'},
                    {field: 'remark',  title: '备注'}
                ]],
                skin: 'line'
            });

        // 监听搜索操作
        form.on('submit(search-btn)', function (data) {
            //执行搜索重载
            table.reload('currentTableId', {
                contentType:'application/json',
                where: data.field
            }, 'data');
            return false;
        });


        /**
    * toolbar事件监听
        */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'save') {   // 监听保存操作
               var checkStatus = table.checkStatus('currentTableId');
               var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行数据修改",{time:1000});
                    return;
                }

                var index = layer.open({
                    title: '修改成绩',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['50%', '50%'],
                    content: '${basePath}/score/score?id='+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });

                <%--$.ajax({--%>
                <%--    url: "${basePath}score/update",--%>
                <%--    type: "POST",--%>
                <%--    dataType:"json",--%>
                <%--    data:{--%>
                <%--        id:data[0].id,--%>
                <%--        score:data[0].score--%>
                <%--    },--%>
                <%--    success:function (data) {--%>
                <%--        layer.msg(data.msg,{time:500},function () {--%>
                <%--            table.reload("currentTableId");--%>
                <%--        })--%>

                <%--    }--%>



            }
        });
    });
</script>
</body>
</html>
