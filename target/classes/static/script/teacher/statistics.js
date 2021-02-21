//获取考试统计
$(function () {
    $.post(getRootPath() + "/teacher/exam/statistics/do/" + $("#eid").val(), null, function (data) {
        if (data.code != 200) {
            //没有人参加此考试
            // $("#exam-title").html("没有考试记录!");
            // $("#wait").hide();
            // $("#pie").hide();
            // $("#charts").show();

            if (confirm("没有考试记录")) {
                window.history.go(-1);
            }

        } else {
            //设置各部分值
            $("#pie").find("img").attr("src", data.data.url);
            $("#highest-point").html(data.data.highestPoint);
            $("#lowest-point").html(data.data.lowestPoint);
            $("#exam-title").html(data.data.title + "考试统计结果(" + data.data.count + "人):");
            //最高分和最低分的人
            var array = data.data.highestNames;
            var str = "";
            for (var i = 0, l = array.length; i < l; i++) {
                str += array[i] + "&nbsp;";
            }
            $("#highest-names").html(str);
            array = data.data.lowestNames;
            str = "";
            for (i = 0, l = array.length; i < l; i++) {
                str += array[i] + "&nbsp;";
            }
            $("#lowest-names").html(str);
            $("#wait").hide();
            $("#charts").show();
        }
    }, "json");
});