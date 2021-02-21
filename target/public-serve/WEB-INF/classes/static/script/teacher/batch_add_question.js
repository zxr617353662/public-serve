

    $(function () {
        $("#batchAdd").click(function () {
            //需要传递文件名
            //获取隐藏域中的文件名
            var postData = "fileName=" + $.trim($("#excel").val());
            $.post("/teacher/other/batchAddQuestion", postData, function (data) {
                if (data.code == 200) {
                    showInfo(data.msg);
                } else if (data.code == 2) {
                    $("#batchAddModal").modal("hide");
                    showInfo(data.data.msg + "，未成功的数据<a href=/download?fileType=3&fileName=" + data.data.fileName + ">点击下载</a>")
                }
            }, "json");
        });
    });


    function showInfo(msg) {
        $("#div_info").html(msg);
        $("#modal_info").modal('show');
    }
