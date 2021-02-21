$(function () {
    $.post("/admin/sys/getSettings", "", function (data) {
        for (var index in data.data) {
            var item = data.data[index];
            if (item.name == "sendEmail") {
                if (item.state == 0) {
                    $("#sendEmail").attr("checked", "checked");
                }
            }
        }
    }, "json");
    $("#sendEmail").change(function () {
        /*chekbox选中返回true，否则为false*/
        var check = $(this).is(":checked");
        var state = 0;
        if (!check) {
            state = 1;
        }
        $.post("/admin/sys/modifyPwdPrompt/" + state, "", function (data) {
            if (data.code) {
                alert(data.msg);
            } else {
                alert("修改失败");
            }
        }, "json");
    });
});