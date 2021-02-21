    /**
     * [[删除单个元素]]
     * @param {[[DOM]]} btn [[触发此函数的按钮]]
     */
    function deleteMajor(btn) {
        var id = $(btn).parent().prev().prev().html();
        if (confirm("这会导致相应的班级及学生被删除,您确定?")) {
            sendDeleteRequest(id);
        }
    }

    /**
     * [[发送删除请求]]
     * @param {[[String]]} [[请求参数]]
     */
    function sendDeleteRequest(id) {
        $.ajax({
            "url": getRootPath()+"/admin/major/delete/" + id,
            "dataType": "json",
            "async": false,
            "success": function (json) {
                if (json.code != 200) {
                    Tips.showError(json.msg);
                } else {
                    Tips.showSuccess(json.msg);

                    setTimeout(function () {
                        window.location.reload();
                    }, 2000);
                    window.location.reload();
                }
            }
        });
    }

    /**
     * [[检查输入的专业]]
     * @param {[[DOM]]} major [[专业输入input元素]]
     * @param {[[DOM]]} error [[显示错误的DOM]]
     */
    function _checkMajor(major, major_value, error) {
        if (major_value == "") {
            error.innerHTML = "请输入专业名称";
            major.focus();
            return false;
        }
        return true;
    }

    /**
     * [[添加专业]]
     * @param {[[DOM]]} form [[所在的表单]]
     */
    function addMajor(form) {
        //获取输入框的值
        var major = form.major;
        var major_value = major.value.trim();
        //获取错误提示的标签
        var error = document.getElementById("major_add_error");
        if (_checkMajor(major, major_value, error)) {
            $.ajax({
                "url": getRootPath()+"/admin/major/add/"+major_value,
                "data": "name=" + major_value,
                "async": false,
                "dataType": "json",
                "success": function (json) {
                    if (json.code === 200) {
                        toggleMajorAdd(false);
                        _resetMajor(major, error);
                        Tips.showSuccess(json.msg);
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    } else {
                        error.innerHTML = json.msg;
                    }
                }
            });
        }
        return false;
    }

    /**
     * [[显示/隐藏专业添加窗口]
     */
    function toggleMajorAdd(isShow) {
        if (isShow) {
            $('#major_add').modal('show');
        } else {
            $('#major_add').modal('hide');
        }
    }

    /**
     * [editMajor 编辑专业]
     */
    function editMajor(form) {
        var major = form.major;
        var major_value = major.value.trim();
        var error = document.getElementById("major_edit_error");
        if (_checkMajor(major, major_value, error)) {
            $.ajax({
                "url": getRootPath()+"/admin/major/edit",
                "data": "id=" + form.id.value + "&name=" + major_value,
                "async": false,
                "dataType": "json",
                "success": function (json) {
                    if (json.code != 200) {
                        error.innerHTML = json.msg;
                    } else {
                        toggleMajorEdit(false);
                        _resetMajor(major, error);
                        Tips.showSuccess(json.msg);
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }
                }
            });
        }
        return false;
    }

    /**
     * [toggleMajorEdit 专业编辑窗口的显示/隐藏]
     * @param  {Boolean} isShow [是否显示]
     * @param  {[type]}  btn    [触发的按钮]
     */
    function toggleMajorEdit(isShow, id,name) {
        if (isShow) {
            //设置专业名称和id
            // var $nameTd = $(btn).parent().prev();
            $("#major_edit_major").val(name);
            $("#major_edit_id").val(id);
            $('#major_edit').modal('show');
        } else {
            $('#major_edit').modal('hide');
        }
    }

    /**
     * [[重置专业输入界面]]
     * @param {[[DOM]]} major [[专业输入]]
     * @param {[[DOM]]} error [[错误显示]]
     */
    function _resetMajor(major, error) {
        major.value = "";
        error.innerHTML = "";
    }

    /**
     * 搜索
     * @param {DOM} 搜搜表单
     */
    function search(form) {
        var input = form.search.value;
        if (input.trim() == "") {
            return false;
        }
        return true;
    }