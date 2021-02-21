    //试卷列表操作
    $(function () {
        ExamList.initListeners();
    });

    /**
     * 跳转试卷管理页面
     * @param examId 试卷id
     * @param examState 试卷当前的状态
     */
    function goExamManager(examId,examState) {
        if (examState === 1) {
            if (confirm("试卷未初始化，是否继续？")) {
                $.post(getRootPath()+"/teacher/exam/status", "examId=" + examId + "&status=2", function (data) {
                    if (data.code != 200) {
                        Tips.showError("错误，请重试");
                    } else {
                        window.location.href = getRootPath()+"/teacher/exam/examManager/" + examId;
                    }
                }, "json");
            }
        } else if (examState === 3) {
            if (confirm("试卷正在运行，是否停止并编辑？")) {
                $.post(getRootPath()+"/teacher/exam/status", "examId=" + examId + "&status=3", function (data) {
                    if (data.code != 200) {
                        Tips.showError("错误，请重试");
                    } else {
                        window.location.href = getRootPath()+"/teacher/exam/examManager/" + examId;
                    }
                }, "json");
            }
        } else {
            window.location.href = getRootPath()+"/teacher/exam/examManager/" + examId;
        }
    }


    var ExamList = {
        //已选的班级的id, 实现已选的班级不再出现在下拉列表
        selectedClazzs: [],
        $gradeOptions: null,
        //缓存当前正在操作的试卷的id
        examId: 0,
        //校验正整数
        numberCheckPattern: /^[1-9][0-9]*$/,
        //辅助函数，获取当前行的试卷的id
        _getId: function (element) {
            return $(element).parents("tr").children("td:first")[0].innerHTML;
        },
        //绑定exam_list.jsp中的事件监听函数
        initListeners: function () {
            var that = this;
            //显示所选班级
            $("#exam-list button[name=show-clazz-btn]").click(function () {
                that.showClass(this);
            });
            //移除所选班级
            $("#remove-clazz-btn").click(that.removeClass);
            //关闭适用班级窗体
            $("#close-clazz-btn").click(that.closeClass);
            //删除试卷监听器
            $("#exam-list button[name=delete-exam-btn]").click(function (event) {
                that.removeExam(event.currentTarget);
            });
            //监听年级和专业的改变事件
            $("#grade_select").change(function () {
                that.loadMajor(this);
            });
            $("#major_select").change(function () {
                that.loadClazz(this);
            });
            //班级添加按钮
            $("#clazz-add-btn").click(that.addClass);
            //班级保存
            $("#clazz-save-btn").click(that.saveClass);
            //开始运行按钮
            $("#exam-list button[name=show-run-time-btn]").click(function () {
                that.showRunTime(this);
            });
            //关闭运行时间设置按钮
            $("#close-run-time-btn").click(that.closeRunTime);
            //保存运行时间
            $("#run-time-save-btn").click(that.saveRunTime);
            //立即停止
            $("#exam-list button[name=stop-run-btn]").click(function () {
                that.stopRun(this);
            });
            //显示试卷编辑界面
            $("#exam-list button[name=show-edit-btn]").click(function () {
                ExamList.showExamEdit(this);
            });
            //关闭试卷编辑界面
            $("#close-edit-btn").click(ExamList.closeExamEdit);
            //试卷保存
            $("#exam-save-btn").click(ExamList.saveExam);

            //试卷保存
            $("#addSubject").click(ExamList.addExam);
        },
        //显示适用的班级
        //button 触发事件的dom对象
        showClass: function (button) {
            //加载适用的班级
            var id = $(button).parent().prev().prev().html();
            ExamList.examId = id;

            $("#clazz-show").modal("show");

            $.post(getRootPath()+"/grade/ajax", null, function (data) {
                if (data.code != 200) {
                    Tips.showError("年级加载失败，请稍后再试");
                } else {
                    var optionsStr = "<option value='0'>年级...</option>";
                    $.each(data.data, function (index, element) {
                        optionsStr += "<option value='" + element.id + "'>" + element.name + "级</option>";
                    });
                    ExamList.$gradeOptions = $(optionsStr);
                    ExamList.$gradeOptions.appendTo($("#grade_select").empty());
                }
            }, "json");
            $.post(getRootPath()+"/teacher/clazz/list", "examId=" + id, function (data) {
                if (data.code !== 200) {
                    Tips.showError(data.msg);
                } else {
                    //渲染班级到clazz-show
                    var classesStr = "";
                    $.each(data.data, function (index, element) {
                        ExamList.selectedClazzs.push(element.id+"");
                        classesStr += "<li><input type='checkbox' /><span value='" + element.id + "'>" + element.gradeDO.name + "级" +
                            element.majorDO.name + element.cno + "班</span></li>";
                    });
                    $(classesStr).appendTo($("#exam-list-container").empty());
                }
            }, "json");

        },
        //关闭班级a
        closeClass: function () {
            $("#clazz-show").hide();
            ExamList.resetSelect();
            ExamList.selectedClazzs.splice(0, ExamList.selectedClazzs.length);//清空数组
        },
        //保存班级
        saveClass: function () {
            $.post(getRootPath()+"/teacher/clazz/reset", "examId=" + ExamList.examId + "&clazzIds=" + ExamList.selectedClazzs.join(), function (data) {
                if (data.code === 2) {
                    Tips.showError("错误非法!");
                } else {
                    Tips.showSuccess(data.msg);
                    setTimeout(function () {
                        window.location.reload();
                    }, 2000);
                }
            }, "json");
            ExamList.closeClass();
        },
        //移除选定的试卷
        //button dom对象，触发事件的按钮
        removeExam: function (button) {
            if (confirm("您确定删除此试题以及所有的成绩记录?")) {
                var id = ExamList._getId(button);
                $.post(getRootPath()+"/teacher/exam/remove", "examId=" + id, function (data) {
                    if (data.result === "0") {
                        Tips.showError("参数错误!");
                    } else if (data.result === "1") {
                        Tips.showSuccess("删除成功");
                        setTimeout(function () {
                            window.location.reload();
                        }, 3000);
                    }
                }, "json");
            }
        },
        //当年级改变时，改变(加载)专业列表
        //gradeSelect 年级下拉列表dom对象
        loadMajor: function (gradeSelect) {
            var selectedGradeId = $(gradeSelect).val();
            var $majorSelect = $("#major_select");
            if (selectedGradeId === "0") {
                //没有选中任何"有意义"的元素，那么清空专业列表(除了提示元素)
                $majorSelect.children("option:gt(0)").remove();
            } else {
                $.post(getRootPath()+"/major/ajax", "grade=" + selectedGradeId, function (data) {
                    if (data.code !== 200) {
                        Tips.showError("专业加载失败，请稍后再试");
                    } else {
                        $majorSelect.empty();
                        var optionsStr = "<option value='0'>专业...</option>";
                        $.each(data.data, function (index, element) {
                            optionsStr += "<option value='" + element.id + "'>" + element.name + "</option>";
                        });
                        $(optionsStr).appendTo($majorSelect);
                    }
                }, "json");
            }
        },
        //同上，改变班级列表
        loadClazz: function (majorSelect) {
            var $clazzSelect = $("#clazz_select");
            var selectedMajorId = $(majorSelect).val();
            var selectedGradeId = $("#grade_select").val();
            if (selectedMajorId === "0") {
                $clazzSelect.children("option:gt(0)").remove();
            } else {
                $.post(getRootPath()+"/clazz/ajax", "grade=" + selectedGradeId + "&major=" + selectedMajorId, function (data) {
                    if (data.code !== 200) {
                        Tips.showError("班级加载失败，请稍候重试");
                    } else {
                        $clazzSelect.empty();
                        var optionsStr = "<option value='0'>班级...</option>";
                        $.each(data.data, function (index, element) {
                            if ($.inArray(element.id+"", ExamList.selectedClazzs) === -1) {
                                optionsStr += "<option value='" + element.id + "'>" + element.cno + "班</option>";
                            }
                        });
                        $(optionsStr).appendTo($clazzSelect);
                    }
                }, "json");
            }
        },
        //添加班级
        addClass: function () {
            var $clazzSelect = $("#clazz_select"),
                $majorSelect = $("#major_select"),
                $gradeSelect = $("#grade_select");
            //需要的数据
            var selectedClazzId, selectedClazzCno, selectedMajorName, selectedGradeGrade;
            if (check()) {
                ExamList.selectedClazzs.push(selectedClazzId+"");
                var optionStr = "<li><input type='checkbox'><span value='" + selectedClazzId + "'>" + selectedGradeGrade + selectedMajorName + selectedClazzCno +
                    "</span></li>";
                $("#exam-list-container").append(optionStr);
                ExamList.resetSelect($gradeSelect, $majorSelect, $clazzSelect);
            }

            //校验，如果成功，那么为上面的数据赋值
            function check() {
                var gOptions = $gradeSelect.children("option:gt(0):selected"),
                    $error = $("#clazz_error");
                if (gOptions.length === 0) {
                    $error.html("请选择年级");
                    return false;
                } else {
                    selectedGradeGrade = gOptions[0].innerHTML;
                }
                var mOptions = $majorSelect.children("option:gt(0):selected");
                if (mOptions.length === 0) {
                    $error.html("请选择专业");
                    return false;
                } else {
                    selectedMajorName = mOptions[0].innerHTML;
                }
                var cOptions = $clazzSelect.children("option:gt(0):selected");
                if (cOptions.length === 0) {
                    $error.html("请选择班级");
                    return false;
                } else {
                    var option = cOptions[0];
                    selectedClazzCno = option.innerHTML;
                    selectedClazzId = option.value;
                }
                $error.html("");
                return true;
            }
        },
        //移除所选班级
        removeClass: function () {
            var $checkeds = $("#exam-list-container input:checked");
            if ($checkeds.length === 0) {
                $("#clazz_error").html("请选择要删除的班级");
            } else {
                var id, $this;
                $checkeds.each(function () {
                    $this = $(this);
                    id = $this.next().attr("value");
                    //从已选班级id数组中移除
                    ExamList.selectedClazzs.splice($.inArray(id, ExamList.selectedClazzs), 1);
                    $this.parent().remove();
                });
                ExamList.resetSelect();
            }
        },
        //重置三个下拉列表
        resetSelect: function ($gradeSelect, $majorSelect, $clazzSelect) {
            var $gs = $gradeSelect || $("#grade_select"),
                $ms = $majorSelect || $("#major_select"),
                $cs = $clazzSelect || $("#clazz_select");
            $gs.empty().append(ExamList.$gradeOptions.clone());
            $ms.empty().append("<option value='0'>专业...</option>");
            $cs.empty().append("<option value='0'>班级...</option>");
        },
        //显示运行时间设置界面
        showRunTime: function (button) {
            //保存正在操作的试卷的id
            ExamList.examId = ExamList._getId(button);
            $("#run-time-set").show();
        },
        //关闭运行时间设置按钮
        closeRunTime: function () {
            //清空输入框的内容的错误提示信息
            var $runTimeSet = $("#run-time-set");
            $runTimeSet.find("input").val("");
            $("#run-time-error").html("");
            $runTimeSet.hide();
        },
        //保存运行天数
        saveRunTime: function () {
            var $runTimeSet = $("#run-time-set");
            var $input = $runTimeSet.find("input");
            var days = $input.val();
            var $error = $("#run-time-error");
            if ($.trim(days) === "") {
                $error.html("请输入天数");
                $input.focus();
                return;
            } else if (!days.match(ExamList.numberCheckPattern)) {
                $error.html("请输入正整数");
                $input.focus();
                return;
            }
            //同步提交
            ExamList._sendStatusRequest(ExamList.examId, "RUNNING", days);
        },
        //发送切换试卷状态请求
        _sendStatusRequest: function (id, status, days) {
            //同步提交
            $.ajax({
                url: getRootPath()+"/teacher/exam/status",
                data: "examId=" + id + "&status=" + status + "&days=" + (days || ""),
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.code === "0") {
                        Tips.showError("参数错误!");
                    } else {
                        Tips.showSuccess("保存成功");
                        setTimeout(function () {
                            window.location.reload();
                        }, 3000);
                    }
                }
            });
        },
        //停止
        stopRun: function (button) {
            var id = ExamList._getId(button);
            //同步提交
            ExamList._sendStatusRequest(id, "RUNNED");
        },
        //显示试卷编辑界面
        //button 触发此事件的按钮
        showExamEdit: function (button) {
            var $examEdit = $("#exam-edit"), $inputs = $examEdit.find("input");
            var $tr = $(button).parents("tr"), $tds = $tr.children("td:lt(2)");
            ExamList.examId = $tds[0].innerHTML;
            //设置试卷题目
            $inputs[0].value = $tds[1].innerHTML;
            //设置时间限制
            $inputs[1].value = $tr.find("input[name=limit]").val();
            $examEdit.show();
            $("#exam-edit").modal("show");
        },
        //关闭试卷编辑界面
        closeExamEdit: function () {
            $("#exam-edit").hide();
        },
        //保存试卷
        saveExam: function () {
            //直接在这里校验
            var $title = $("#title-value");
            var title = $.trim($title.val());
            if (title === "") {
                getErrorSpan($title).html("请输入内容");
                $title.focus();
                return;
            }
            var $limit = $("#limit-value");
            var limit = $.trim($limit.val());
            if (limit === "") {
                getErrorSpan($limit).html("请输入内容");
                $limit.focus();
                return;
            }
            if (!limit.match(ExamList.numberCheckPattern)) {
                getErrorSpan($limit).html("格式非法");
                $limit.focus();
                return;
            }
            //提交请求
            $.post(getRootPath()+"/teacher/exam/update/" + ExamList.examId, "title=" + title + "&limit=" + limit, function (data) {
                if (data.code !== 200) {
                    Tips.showError("更新失败，请稍候再试");
                } else {
                    Tips.showSuccess("更新成功");
                    setTimeout(function () {
                        window.location.reload();
                    }, 2000);
                }
            }, "json");

            function getErrorSpan($ele) {
                return $ele.parent().next().children("span");
            }
        }, addExam: function () {
            var postdata = "title=" + $.trim($("#addSubjectName").val()) + "&timelimit=" + $.trim($("#addSubjectTime").val());
            $.post(getRootPath()+"/teacher/exam/add", postdata, function (data) {
                if (data.code !== 200) {
                    Tips.showError("添加失败，请稍候再试");
                } else {
                    $("#addModal").modal("hide");
                    Tips.showSuccess("添加成功");
                    setTimeout(function () {
                        window.location.reload();
                    }, 2000);
                }
            }, "json");
        }
    };



