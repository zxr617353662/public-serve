$(document).ready(function () {
    $("#header .head_chose .nav li").mouseenter(function () {
        console.log("over");
        $(this).addClass("active")
    })
    $("#header .head_chose .nav li").mouseleave(function () {
        console.log("leave1");
        $(this).removeClass("active")
    })
})