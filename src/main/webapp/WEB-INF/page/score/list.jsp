<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>专业管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
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
                url: '${basePath}score/query',
                contentType:'application/json',
                method:"post",
                where: {stuId: ${user.id}},
                toolbar: '#toolbar',
                defaultToolbar: ['filter', 'exports', 'print'],
                page: true,
                cols: [[
                    {field: 'year', width: 80, title: '年度',templet:'<div>{{d.section.year}}</div>'},
                    {field: 'type',  title: '类型',templet:'<div>{{d.section.type}}</div>'},
                    {field: 'clazzName', title: '所属班级',templet:'<div>{{d.clazz.clazzName}}</div>'},
                    {field: 'courseName', title: '课程',templet:'<div>{{d.course.courseName}}</div>'},
                    {field: 'teacherName', title: '老师',templet:'<div>{{d.teacher.teacherName}}</div>'},
                    {field: 'score',  title: '成绩'},
                    {field: 'remark',  title: '备注'}
                ]],
                done: function(res, page, count){
                    for(var index in res.data){
                        if(res.data[index].checked >= 1){
                            res.data[index]["LAY_CHECKED"]='true';
                            var index= res.data[index]['LAY_TABLE_INDEX'];
                            $('tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                            $('tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                        }
                    }
                },
                skin: 'line'
            });

    });
</script>
</body>
</html>
