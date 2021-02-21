﻿//学生参加考试
$(function () {

    //是否结束
    var flag = false;
    countTime();
    //绑定提交按钮
    $("#submit-btn").click(submit);
    //监听窗口关闭或刷新事件，如果还没有做完，给用户一个提示
    window.onbeforeunload = function () {
        if (!flag) {
            window.event.returnValue = "考试尚未结束，您确定离开?";
        }
    };

    /**
     * 计时函数
     */
    function countTime() {
        // 显示倒计时的文本控件
        var $showTime = $("#count-time");
        var limit = $("#time-limit").val(),
            //分钟数
            minutes = parseInt(limit),
            mf = 0,
            ms = 0;
        var id = setInterval(function () {
            if (ms > 0) {
                --ms;
                _setTime();
            } else if (mf > 0) {
                ms = 9;
                --mf;
                _setTime();
            } else if (minutes > 0) {
                mf = 5;
                ms = 9;
                --minutes;
                _setTime();
            } else {
                flag = true;
                //计时结束
                clearInterval(id);
                Tips.showMessage("时间已到，系统正在交卷...");
                submit();
            }
        }, 1000);

        //设置时间
        function _setTime() {
            $showTime.html(minutes + ":" + mf + ms);
        }
    }

    /**
     * 交卷
     * 提交的json串的格式:
     * {
     * 		eid: 1,//试卷id
     * 		questions:[
     * 			{id: 2, answer: "1,2"}
     * 		]
     * }
     */
    function submit() {
        var result = {};
        result.examId = $("#exam-id").val();
        result.questions = [];
        result.singles = [];
        result.multis = [];
        result.jugdes = [];
        //处理单选题
        var $singles = $("#single-container").find("div.question"), id, answer, $question, $checkeds;
        $.each($singles, function (index, question) {
            $question = $(question);
            id = $question.find("input[name=question-id]").val();
            //如果没有选中，那么
            $checkeds = $question.find("input:checked");
            answer = $checkeds.length > 0 ? $checkeds.val() : "";
            result.singles.push({
                id: id,
                type: 1,
                answer: answer
            });
        });
        //处理多选题
        $("#multi-container").find("div.question").each(function (index, question) {
            $question = $(question);
            id = $question.find("input[name=question-id]").val();
            //拼接答案串
            answer = "";
            $question.find("input:checked").each(function () {
                answer += (this.value + ",");
            });
            result.multis.push({
                id: id,
                type: 2,
                answer: answer.substring(0, answer.length - 1)
            });
        });
        //处理判断题
        $("#judge-container").find("div.question").each(function (index, question) {
            $question = $(question);
            id = $question.find("input[name=question-id]").val();
            $checkeds = $question.find("input:checked");
            answer = $checkeds.length > 0 ? $checkeds.val() : "";
            result.jugdes.push({
                id: id,
                type: 3,
                answer: answer
            });
        });
        // JSON.stringify 将对象转成json格式的字符串
        $.post(getRootPath() + "/student/exam/submit", "result=" + JSON.stringify(result), function (data) {
            if (data.code != 200) {
                Tips.showError(data.msg);
                setTimeout(function () {
                    window.location.href = getRootPath()+"/student/index";
                }, 2000);
            } else {
                Tips.showSuccess("交卷成功，您得了" + data.data.point + "分!");
                setTimeout(function () {
                    window.location.href = getRootPath()+"/student/index";
                }, 2000);
            }
        }, "json");
    }

});