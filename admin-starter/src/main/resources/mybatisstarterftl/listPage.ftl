<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>layuiAdmin 后台管理员</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layuiadmin/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/layuiadmin/css/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-block">
                        <input type="text" name="loginname" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机</label>
                    <div class="layui-input-block">
                        <input type="text" name="telphone" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">角色</label>
                    <div class="layui-input-block">
                        <select name="roles">
                            <option value="0">管理员</option>
                            <option value="1">超级管理员</option>
                            <option value="2">纠错员</option>
                            <option value="3">采购员</option>
                            <option value="4">推销员</option>
                            <option value="5">运营人员</option>
                            <option value="6">编辑</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-user-back-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
                <button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
            </div>

            <table id="LAY-user-back-manage" lay-filter="LAY-user-back-manage"></table>
            <script type="text/html" id="buttonTpl">
                {{#  if(d.status == 1){ }}
                <button class="layui-btn layui-btn-xs">已审核</button>
                {{#  } else { }}
                <button class="layui-btn layui-btn-primary layui-btn-xs">未审核</button>
                {{#  } }}
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                {{#  if(d.role == '超级管理员'){ }}
                <a class="layui-btn layui-btn-disabled layui-btn-xs"><i class="layui-icon layui-icon-delete"></i>删除</a>
                {{#  } else { }}
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
                {{#  } }}
            </script>
        </div>
    </div>
</div>

<script src="/static/layuiadmin/layui.js"></script>
<script th:inline="none">
    layui.config({
        base: '/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function(){
        var $ = layui.$
            ,form = layui.form
            ,table = layui.table;

        table.render({
            elem: '#LAY-user-back-manage'
            ,height: 500
            ,url: '/admin/query'
            ,method: 'post'
            ,page: true //开启分页
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width: 80, sort: true}
                ,{field: 'loginname', title: '登录名'}
                ,{field: 'telphone', title: '手机'}
                ,{field: 'email', title: '邮箱'}
                ,{field: 'role', title: '角色'}
                ,{field: 'createTime', title: '加入时间'}
                ,{field: 'status', title: '审核状态', templet: '#buttonTpl', align: 'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width: 150}
            ]]
        });

        //监听搜索
        form.on('submit(LAY-user-back-search)', function(data){
            var field = data.field;

            //执行重载
            table.reload('LAY-user-back-manage', {
                where: field
            });
        });

        //事件
        var active = {
            batchdel: function(){
                var checkStatus = table.checkStatus('LAY-user-back-manage')
                    ,checkData = checkStatus.data; //得到选中的数据

                if(checkData.length === 0){
                    return layer.msg('请选择数据');
                }
                layer.confirm('确定删除吗？', function(index) {

                    //执行 Ajax 后重载
                    /*
                    admin.req({
                      url: 'xxx'
                      //,……
                    });
                    */
                    table.reload('LAY-user-back-manage');
                    layer.msg('已删除');
                });

            }
            ,add: function(){
                layer.open({
                    type: 2
                    ,title: '添加管理员'
                    ,content: 'add'
                    ,area: ['420px', '420px']
                    ,btn: ['确定', '取消']
                    ,yes: function(index, layero){
                        var iframeWindow = window['layui-layer-iframe'+ index]
                            ,submitID = 'LAY-add-submit'
                            ,submit = layero.find('iframe').contents().find('#'+ submitID);

                        //监听提交
                        iframeWindow.layui.form.on('submit('+submitID+')', function(data){
                            var field = data.field; //获取提交的字段
                            $.ajax({
                                type: "post",
                                url: "/admin/insert",
                                data: field,
                                dataType: "json",
                                success: function(data){
                                    if(data.code==0) {
                                        layer.msg("成功", {time: 1000});
                                    }else{
                                        layer.msg(data.msg, {time: 1000});
                                    }
                                }
                            });
                            table.reload('LAY-user-back-manage'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                        submit.trigger('click');
                    }
                });
            }

        }

        $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        table.on("tool(LAY-user-back-manage)", function(e) {
            var data = e.data;
            if ("del" === e.event) {
                layer.confirm("确定删除？", function(t) {
                    e.del(), layer.close(t)
                    $.ajax({
                        type: "get",
                        url: "/admin/delete?id="+data.id,
                        dataType: "json",
                        success: function(data){}
                    });
                });
            }else if ("edit" === e.event) {
                layer.open({
                    type: 2,
                    title: "编辑管理员",
                    content: "edit",
                    area: ["420px", "420px"],
                    btn: ["确定", "取消"],
                    yes: function(e, t) {
                        var l = window["layui-layer-iframe" + e],
                            r = "LAY-edit-submit",
                            n = t.find("iframe").contents().find("#" + r);
                        l.layui.form.on("submit(" + r + ")", function(t) {
                            $.ajax({
                                type: "post",
                                url: "/admin/update",
                                data: t.field,
                                dataType: "json",
                                success: function(data){
                                    if(data.code==0) {
                                        layer.msg("成功", {time: 1000});
                                    }else{
                                        layer.msg(data.msg, {time: 1000});
                                    }
                                }
                            });
                            table.reload('LAY-user-back-manage'), layer.close(e)
                        }), n.trigger("click")
                    },
                    success: function(layero, index) {
                        var body = layui.layer.getChildFrame('body', index);
                        body.find("[name='id']").val(data.id);
                        body.find("[name='loginname']").val(data.loginname);
                        body.find("[name='telphone']").val(data.telphone);
                        body.find("[name='email']").val(data.email);
                        body.find("[name='role']").val(data.role);
                        body.find("[name='status']").val(data.status);
                        body.find("[name='status-switch']").attr("checked", data.status==1?true:false);
                        //form.render();
                    }
                })
            }
        });

    });
</script>
</body>
</html>