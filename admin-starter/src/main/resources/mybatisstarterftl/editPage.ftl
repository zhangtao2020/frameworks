<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layuiAdmin 管理员 iframe 框</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layuiadmin/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">

    <#list entity.propList as prop>
        <div class="layui-form-item">
            <label class="layui-form-label">${prop.note}</label>
            <div class="layui-input-inline">
                <input type="text" name="${prop.propName}" lay-verify="required" placeholder="请输入${prop.note}" class="layui-input">
            </div>
        </div>
    </#list>

    <div class="layui-form-item layui-hide">
        <input type="hidden" name="id"/>
        <input type="button" lay-submit lay-filter="LAY-edit-submit" id="LAY-edit-submit" value="确认">
    </div>
</div>

<script src="/static/layuiadmin/layui.js"></script>
<script>
    layui.config({
        base: '/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function(){
        var $ = layui.$
            ,form = layui.form;

        // 监听审核状态
        form.on('switch(status-switch)', function(obj){
            if(obj.elem.checked){
                $("input[name='status']").val(1);
            }else{
                $("input[name='status']").val(0);
            }
        })
    })
</script>
</body>
</html>