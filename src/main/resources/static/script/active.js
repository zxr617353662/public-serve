$(document).ready(function () {
    $("#header .head_chose .nav li").mouseenter(function () {
        $(this).addClass("active")
    })
    $("#header .head_chose .nav li").mouseleave(function () {
        $(this).removeClass("active")
    })
})