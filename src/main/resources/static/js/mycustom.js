/**
 * 注册
 * @returns {boolean}
 */
function submitChangePassword() {
    let password0 = $('#password0').val().trim();
    let password1 = $('#password1').val().trim();
    let password2 = $('#password2').val().trim();
    if (password1 != password2){
        xtip.msg('两次输入的密码不一致!');
        return false;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: "changePassword",
        dataType: "json",
        data: {old: password0,password:password1},
        success: function (data) {
            if (data['code'] == 200) {
                xtip.msg('修改成功！');
                setTimeout("reload()", 1000);
            }else if (data['code'] == 201){
                xtip.msg('原密码错误！');
            }
        },
        error: function (e) {
        }
    });
    return false;
}
/**
 * 注册
 * @returns {boolean}
 */
function submitRegister() {
    let username1 = $('#username1').val().trim();
    let password1 = $('#password1').val().trim();
    let password2 = $('#password2').val().trim();
    if (password1 != password2){
        xtip.msg('两次输入的密码不一致!');
        return false;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: "register",
        dataType: "json",
        data: {name: username1,password:password1},
        success: function (data) {
            if (data['code'] == 200) {
                xtip.msg('注册成功！即将跳转到首页...');
                setTimeout("reloadToIndex()", 1000);
            }else if (data['code'] == 201) {
                xtip.msg('用户名已被占用！');
            }
        },
        error: function (e) {
        }
    });
    return false;
}
/**
 * 登录
 * @returns {boolean}
 */
function submitLogin() {
    let name = $('#username').val().trim();
    let password = $('#password').val().trim();
    $.ajax({
        async: false,
        type: "POST",
        url: "login",
        dataType: "json",
        data: {name:name,password:password},
        success: function (data) {
            if (data['code'] == 200) {
                xtip.msg('登录成功！即将跳转到首页...');
                setTimeout("reloadToIndex()", 1000);
            }else if (data['code'] == 201) {
                xtip.msg('密码错误！');
            }else if (data['code'] == 202){
                xtip.msg('尚未注册的用户名！');
            }
        },
        error: function (e) {
        }
    });
    return false;
}

/**
 * 删除分类
 */
function deleteKind(id) {
    xtip.win({
        icon:'a',
        tip: '确定要删除此分类吗？',
        title: '删除分类',
        width: '400px',
        btn1: function() {
            $.ajax({
                async: false,
                type: "POST",
                url: "deleteKind",
                dataType: "json",
                data: {id: id},
                success: function (data) {
                    if (data['code'] == 200) {
                        xtip.msg('删除成功！');
                        setTimeout("reload()", 1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}
/**
 * 更新分类名称
 */
function updateKind(id,dom) {
    let name = $(dom).val().trim();
    if (name == ''){
        xtip.msg('分类名称不能为空！');
    }else {
        $.ajax({
            async: false,
            type: "POST",
            url: "updateKind",
            dataType: "json",
            data: {id:id,name:name},
            success: function (data) {
                if (data['code']== 200){
                    xtip.msg('修改成功！');
                    setTimeout("reload()",1000);
                }else if (data['code']== 201){
                    xtip.msg('已存在的分类！');
                    setTimeout("reload()",1000);
                }
            },
            error: function (e) {
            }
        });
    }
}
/**
 * 添加分类
 */
function showAddKind() {
    xtip.win({
        tip: '输入新分类的名称： <br><br>' +
            '<input class="form-control" id="kName" style="width: 100%" type="text"><br>',
        title: '添加分类',
        width: '400px',
        btn1: function(){
            let kName = $("#kName").val().trim();
            if (kName == ''){
                xtip.msg('分类名称不能为空！');
            }else {
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "addKind",
                    dataType: "json",
                    data: {name:kName},
                    success: function (data) {
                        if (data['code']== 200){
                            xtip.msg('添加成功！');
                            setTimeout("reload()",1000);
                        }else if (data['code']== 201){
                            xtip.msg('已存在的分类！');
                        }
                    },
                    error: function (e) {
                    }
                });
            }
        }
    });
}

/**
 * 添加链接
 */
function showAddLink() {
    xtip.win({
        tip:
            '<div class="form-group">\n' +
            '    <div class="col-md-4"><label>类型：</label></div>\n' +
            '    <div class="col-md-8">\n' +
            '        <select id="type" class="form-control">\n' +
            '            <option value="1">顶部菜单</option>\n' +
            '            <option value="2">友情链接</option>\n' +
            '        </select>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-4"><label>名称：</label></div>\n' +
            '    <div class="col-md-8"><input id="lName" type="text" class="form-control" name="car_number" value="" /></div>\n' +
            '</div>\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-4"><label>链接：</label></div>\n' +
            '    <div class="col-md-8"><input id="url" type="text" class="form-control" name="car_number" value="" /></div>\n' +
            '</div>\n',
        title: '添加链接/顶部菜单',
        width: '400px',
        btn1: function () {
            let name = $('#lName').val().trim();
            let url = $('#url').val().trim();
            let type = $("#type").find("option:selected").val();
            $.ajax({
                async: false,
                type: "POST",
                url: "addLink",
                dataType: "json",
                data: {name: name, url: url, type: type},
                success: function (data) {
                    if (data['code'] == 200) {
                        xtip.msg('添加成功！');
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 修改素材基本信息
 */
function showEditMaterial(id,name,info) {
    xtip.win({
        tip:
            '<div class="space">\n' +
            '    <label>素材名称 : </label>\n' +
            '    <input type="text" class="form-control" id="name" name="name" value="'+name+'">\n' +
            '</div>\n' +
            '<div class="space">\n' +
            '    <label>描述(支持HTML标签) : </label>\n' +
            '    <textarea style="resize: vertical" class="form-control" id="info" name="info">'+info+'</textarea>\n' +
            '</div>',
        title: '修改素材',
        width: '400px',
        btn1: function () {
            let name = $('#name').val().trim();
            let info = $('#info').val().trim();
            $.ajax({
                async: false,
                type: "POST",
                url: "editMaterial",
                dataType: "json",
                data: {id: id, name: name, info: info},
                success: function (data) {
                    if (data['code'] == 200) {
                        xtip.msg('提交成功！等待管理员审核！');
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 反馈
 */
function feedback() {
    xtip.win({
        icon: 'h',
        tip: '若您有任何疑问，欢迎联系作者 <br><br>' +
            '<input style="width: 100%" disabled type="text" value="QQ：373675032" ><br>',
        title: '反馈',
        width: '400px'
    });
}

/**
 * 搜索
 */
function search() {
    let content = $("#search").val().trim();
    if (content == ""){
        xtip.msg("请输入查询内容");
        return;
    }
    let href = window.location.href;
    href = href.split("/su-share/")[0] + "/su-share/materials?kId=-1&search="+content;
    reloadTo(href);
}
/**
 * 将某个消息设为已读
 * @param id
 */
function readMessage(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: "readMessage",
        dataType: "json",
        data: {id:id},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("设为已读成功！");
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 全部已读
 */
function readAll() {
    $.ajax({
        async: false,
        type: "POST",
        url: "readAll",
        dataType: "json",
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("全部已读成功！");
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 全部已读
 */
function deleteAll() {
    $.ajax({
        async: false,
        type: "POST",
        url: "deleteAll",
        dataType: "json",
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("全部删除成功！");
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 将某个消息删除
 * @param id
 */
function deleteMessage(id) {
    xtip.win({
        icon:'a',
        tip: '确定要删除此消息吗？',
        title: '删除消息',
        width: '400px',
        btn1: function() {
            $.ajax({
                async: false,
                type: "POST",
                url: "deleteMessage",
                dataType: "json",
                data: {id:id},
                success: function (data) {
                    if (data['code']== 200){
                        xtip.msg("删除消息成功！");
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 查看素材的分类
 */
function changeMaterialKind() {
    let type = $("#kId").find("option:selected").val();
    let href = window.location.href;
    if (type == 0){
        href = href.split("/su-share/")[0] + "/su-share/index";
    }else {
        href = href.split("/su-share/")[0] + "/su-share/materials?kId="+type;
    }
    reloadTo(href);
}

/**
 * 修改审核队列的查看状态
 */
function changeAuditType() {
    let type = $("#type").find("option:selected").val();
    let href = window.location.href;
    href = href.split("/su-share/")[0] + "/su-share/audit?type="+type;
    reloadTo(href);
}

/**
 * 修改我的消息的查看状态
 */
function changeMessageType() {
    let type = $("#type").find("option:selected").val();
    let href = window.location.href;
    href = href.split("/su-share/")[0] + "/su-share/messages?type="+type;
    reloadTo(href);
}

/**
 * 处理素材
 */
function dealMaterial(id) {
    xtip.win({
        tip: '<select id="deal" class="form-control">' +
                '<option value="1">通过</option>' +
                '<option value="2">不通过</option>' +
            '</select><br>' +
            '<textarea id="info" class="form-control" placeholder="在此处填写处理意见..." style="resize: none;height:200px"></textarea>',
        title: '处理素材',
        width: '400px',
        btn1: function(){
            let deal = $("#deal").find("option:selected").val();
            let info = $('#info').val().trim();
            $.ajax({
                async: false,
                type: "POST",
                url: "dealMaterial",
                dataType: "json",
                data: {id:id,deal:deal,info:info},
                success: function (data) {
                    if (data['code']== 200){
                        xtip.msg("处理成功！");
                        setTimeout("reload()",1000);
                    }else {
                        xtip.msg("服务器出错啦！");
                    }
                },
                error: function (e) {
                }
            });
        },
    });
}

/**
 * 删除链接
 * @param id
 */
function deleteLink(id) {
    xtip.win({
        icon:'a',
        tip: '确定要删除此链接吗？',
        title: '删除链接',
        width: '400px',
        btn1: function() {
            $.ajax({
                async: false,
                type: "POST",
                url: "deleteLink",
                dataType: "json",
                data: {id:id},
                success: function (data) {
                    if (data['code']== 200){
                        xtip.msg("删除链接成功！");
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 更新公告
 */
function updateNotice() {
    let notice = $('#notice').val().trim();
    $.ajax({
        async: false,
        type: "POST",
        url: "updateNotice",
        dataType: "json",
        data: {content:notice},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("公告更新成功！");
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}
/**
 * 检查上传的素材信息
 * @returns {boolean}
 */
function checkNew() {
    let name = $("#name").val().trim();
    let info = $("#info").val().trim();
    let face = $("#face").get(0).files[0];
    let source = $("#source").get(0).files[0];

    if (name == ''){
        xtip.msg("名称不能为空");
        return false;
    }
    if (info == ''){
        xtip.msg("描述不能为空");
        return false;
    }
    if (!face){
        xtip.msg("未上传封面");
        return false;
    }
    if (!source){
        xtip.msg("未上传素材源文件");
        return false;
    }

    return true;
}

/**
 * 下载素材
 * @param id
 */
function downloadMaterial(id) {
    $.ajax({
        async: false,
        type: "GET",
        url: "downloadMaterial",
        dataType: "json",
        data: {id:id},
        success: function (data) {
            if (data['code']== 200){
                reloadTo(data['url']);
            }else if (data['code'] == 402){
                xtip.msg("你已经被禁止下载任何文件！");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 上传用户头像
 */
function uploadAvatar() {
    var formdata = new FormData();
    formdata.append("avatar", $("#avatar").get(0).files[0]);
    $.ajax({
        async: false,
        type: "POST",
        url: "uploadAvatar",
        dataType: "json",
        data: formdata,
        contentType: false,//ajax上传图片需要添加
        processData: false,//ajax上传图片需要添加
        success: function (data) {
            if (data.code == 200) {
                xtip.msg("头像上传成功!")
                setTimeout("reload()",1000);
            } else if (data.code == 500) {
                xtip.msg("头像上传失败!")
            }
        },
        error: function (e) {
            xtip.msg("头像上传失败！");
        }
    });
}

/**
 * 修改资料
 */
function updateProfile() {
    var name = $("#name").val().trim();
    var info = $("#info").val().trim();
    var sex = $("#sex  option:selected").val();

    if (name == ''){
        xtip.msg("昵称不能为空！");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: "updateProfile",
        dataType: "json",
        data: {name:name,info:info,sex:sex},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("修改成功！");
            }else if (data['code'] == 201){
                xtip.msg("用户名已被占用！");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 添加收藏,取消收藏
 * @param mId
 */
function updateFavorite(mId) {
    $.ajax({
        async: false,
        type: "POST",
        url: "updateFavorite",
        dataType: "json",
        data: {mId:mId},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("收藏成功！");
                setTimeout("reload()",1000);
            }else if (data['code']== 201) {
                xtip.msg("取消收藏成功！");
                setTimeout("reload()",1000);
            }else {
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 发表评论
 */
function publishComment(id) {
    var starCount = $("#starCount").val().trim();
    var comment = $("#comment").val().trim();
    if (comment == ""){
        xtip.msg("请输入评论内容！");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: "addComment",
        dataType: "json",
        data: {starCount:starCount,comment:comment,mId:id},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("发表评论成功！");
                setTimeout("reload()",1000);
            }else {
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 显示回复的输入框
 */
function showReply(id,name,content) {
    $('#reply').css("display","");
    $('#replyId').val(id);
    $('#reply-content').attr('placeholder',"回复 @"+name+"："+content);
    $('#reply-content').focus();
}

/**
 * 回复评论
 */
function replyComment(id) {
    var replyId = $("#replyId").val().trim();
    var content = $("#reply-content").val().trim();
    if (content == ''){
        xtip.msg("请输入回复内容！");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: "replyComment",
        dataType: "json",
        data: {replyId:replyId,content:content,mId:id},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("回复评论成功！");
                setTimeout("reload()",1000);
            }else {
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 删除评论
 * @param id
 */
function deleteComment(id) {
    xtip.win({
        icon:'a',
        tip: '确定要删除此评论吗？',
        title: '删除评论',
        width: '400px',
        btn1: function() {
            $.ajax({
                async: false,
                type: "POST",
                url: "deleteComment",
                dataType: "json",
                data: {id:id},
                success: function (data) {
                    if (data['code']== 200){
                        xtip.msg("删除成功！");
                        setTimeout("reload()",1000);
                    }else {
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 移入回收站
 * @param id
 */
function intoTrash(id) {
    xtip.win({
        icon:'a',
        tip: '确定要将此素材移入回收站吗？<br> <small>移入回收站后，您还可以复原它</small>>',
        title: '移入回收站',
        width: '400px',
        btn1: function() {
            $.ajax({
                async: false,
                type: "POST",
                url: "intoTrash",
                dataType: "json",
                data: {mId:id},
                success: function (data) {
                    if (data['code']== 200){
                        xtip.msg("移入回收站成功！");
                        setTimeout("reload()",1000);
                    }else {
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 删除素材
 * @param id
 */
function deleteMaterial(id) {
    xtip.win({
        icon:'a',
        tip: '确定要将永久删除此素材吗？<br> <small>不可恢复</small>>',
        title: '删除素材',
        width: '400px',
        btn1: function() {
            $.ajax({
                async: false,
                type: "POST",
                url: "deleteMaterial",
                dataType: "json",
                data: {mId:id},
                success: function (data) {
                    if (data['code']== 200){
                        xtip.msg("删除素材成功！");
                        setTimeout("reload()",1000);
                    }else {
                        setTimeout("reload()",1000);
                    }
                },
                error: function (e) {
                }
            });
        }
    });
}

/**
 * 恢复素材，移出回收站
 * @param id
 */
function restoreMaterial(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: "restoreMaterial",
        dataType: "json",
        data: {mId:id},
        success: function (data) {
            if (data['code']== 200){
                xtip.msg("移出成功！");
                setTimeout("reload()",1000);
            }else {
                setTimeout("reload()",1000);
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 重新加载页面
 */
function reload() {
    window.location.reload();
}

/**
 * 跳转到指定页面
 * @param url
 */
function reloadTo(url) {
    window.location.href = url;
}

/**
 * 跳转到主页
 * @param url
 */
function reloadToIndex() {
    let href = window.location.href;
    href = href.split("/su-share/")[0];
    window.location.href = href + "/su-share/";
}

/**
 * 分享本站
 */
function shareIndex() {

    let href = window.location.href;
    href = href.split("/su-share/")[0];
    xtip.win({
        icon: 'h',
        tip: '[Ctrl + C] 复制！然后快去粘贴吧~ <br><br>' +
            '<input id="shareInput" style="width: 100%" type="text" value="'+href+'/su-share/" autofocus="true"><br><br>',
        title: '分享本站',
        width: '400px'
    });
    $("#shareInput").focus();
    $("#shareInput").select();
}

/**
 * 分享素材
 * @param id
 */
function shareMaterial(id) {
    let href = window.location.href;
    href = href.split("/su-share/")[0]+"/su-share/material?id="+id;
    xtip.win({
        icon: 'h',
        tip: '[Ctrl + C] 复制！然后快去粘贴吧~ <br><br>' +
            '<input id="shareInput" style="width: 100%" type="text" value="'+href+'" autofocus="true"><br><br>',
        title: '分享素材',
        width: '400px'
    });
    $("#shareInput").focus();
    $("#shareInput").select();
}