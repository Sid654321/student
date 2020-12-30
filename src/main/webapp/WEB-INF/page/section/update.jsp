<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${basePath}static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${basePath}static/css/style.css" media="all">
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main width_60">
        <form class="layui-form">
            <input type="hidden" value="${section.id}" name="id">
            <input type="hidden" value="${section.clazzId}" name="clazzId">
            <div class="layui-form-item">
                <label class="layui-form-label">年度</label>
                <div class="layui-input-block">
                    <input type="text" name="year" class="layui-input" value="${section.year}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <input type="text" name="type" lay-verify="required" class="layui-input" value="${section.type}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="remark" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">老师</label>
                <div class="layui-input-block">
                    <select name="teacherId" lay-verify="required">
                        <option value="">请选择老师</option>
                        <c:forEach items="${teachers}" var="teacher">
                            <option value="${teacher.id}" <c:if test="${section.teacherId==teacher.id}">selected</c:if>>${teacher.teacherName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">课程</label>
                <div class="layui-input-block">
                    <select name="courseId" lay-verify="required">
                        <option value="">请选择课程</option>
                        <c:forEach items="${courses}" var="course">
                            <option value="${course.id}" <c:if test="${section.courseId==course.id}">selected</c:if>>${course.courseName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-primary layui-btn-sm data-add-btn">
                        <i class="fa fa-refresh"></i>
                        重置
                    </button>
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-submit lay-filter="save">
                        <i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${basePath}static/lib/layui-src/layui.js" charset="utf-8"></script>
<script src="${basePath}static/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['form','jquery','laydate'], function () {
        var form = layui.form,$ = layui.jquery,laydate = layui.laydate;

        laydate.render({
            elem:'#birthday'
        })
        laydate.render({
            elem:'#joinDate'
        })
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);
        //监听提交
        form.on('submit(save)', function (data) {
            $.ajax({
                url:"${basePath}section/update",
                type:"POST",
                contentType:'application/json',
                dataType:'json',
                data:JSON.stringify(data.field),
                success:function(data){
                    layer.msg(data.msg,{time:500},
                        function(){
                            parent.layer.close(index);
                        });
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
