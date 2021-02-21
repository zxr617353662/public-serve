// $(function () {
//     var $form = $("#password-form"),
//         form = $form[0],
//         // oldPassword = form.oldPassword,
//         // newPassword = form.newPassword,
//         newPasswordConfirm = form.newPasswordConfirm;
//     var $errors = $form.find("span.password-error");
//     var opError = $errors[0],
//         npError = $errors[1],
//         npcError = $errors[2];
//     //规定密码由4-10位的字母数字下划线组成
//     var passwordPattern = /^\w{4,10}$/;
//     // $(oldPassword).blur(checkOldPassword).focus(function () {
//     //     // this.value = "";
//     //     opError.innerHTML = "";
//     // });
//     $(newPassword).blur(checkPassword).focus(function () {
//         // this.value = "";
//         npError.innerHTML = "";
//     });
//     // $(newPasswordConfirm).blur(checkConfirm).focus(function () {
//     //     // this.value = "";
//     //     npcError.innerHTML = "";
//     // });
//     $form.submit(function () {
//         return checkOldPassword() && checkPassword() && checkConfirm();
//     });
//
//     function checkOldPassword() {
//         var value = $.trim(oldPassword.value);
//         if (value === "") {
//             opError.innerHTML = "请输入旧密码";
//             return false;
//         }
//         //发送ajax请求校验
//         var flag = true;
//         $.ajax({
//             url: getRootPath() + "/admin/password/check",
//             dataType: "json",
//             data: "password=" + value,
//             async: false,
//             success: function (data) {
//                 if (data.code != 200) {
//                     opError.innerHTML = "您的密码错误";
//                     flag = false;
//                 }
//             }
//         });
//         return flag;
//     }
//
//     function checkPassword() {
//         var value = $.trim(newPassword.value);
//         if (value === "") {
//             npError.innerHTML = "请输入您的新密码";
//             return false;
//         }
//         if (!value.match(passwordPattern)) {
//             npError.innerHTML = "您的密码格式非法";
//             return false;
//         }
//         return true;
//     }
//
//     function checkConfirm() {
//         if (newPassword.value !== newPasswordConfirm.value) {
//             npcError.innerHTML = "您的两此新密码不一致";
//             return false;
//         }
//         return true;
//     }
// });$(function () {
//     var $form = $("#password-form"),
//         form = $form[0],
//         oldPassword = form.oldPassword,
//         newPassword = form.newPassword,
//         newPasswordConfirm = form.newPasswordConfirm;
//     var $errors = $form.find("span.password-error");
//     var opError = $errors[0],
//         npError = $errors[1],
//         npcError = $errors[2];
//     //规定密码由4-10位的字母数字下划线组成
//     var passwordPattern = /^\w{4,10}$/;
//     $(oldPassword).blur(checkOldPassword).focus(function () {
//         // this.value = "";
//         opError.innerHTML = "";
//     });
//     $(newPassword).blur(checkPassword).focus(function () {
//         // this.value = "";
//         npError.innerHTML = "";
//     });
//     $(newPasswordConfirm).blur(checkConfirm).focus(function () {
//         // this.value = "";
//         npcError.innerHTML = "";
//     });
//     $form.submit(function () {
//         return checkOldPassword() && checkPassword() && checkConfirm();
//     });
//
//     function checkOldPassword() {
//         var value = $.trim(oldPassword.value);
//         if (value === "") {
//             opError.innerHTML = "请输入旧密码";
//             return false;
//         }
//         //发送ajax请求校验
//         var flag = true;
//         $.ajax({
//             url: getRootPath() + "/admin/password/check",
//             dataType: "json",
//             data: "password=" + value,
//             async: false,
//             success: function (data) {
//                 if (data.code != 200) {
//                     opError.innerHTML = "您的密码错误";
//                     flag = false;
//                 }
//             }
//         });
//         return flag;
//     }
//
//     function checkPassword() {
//         var value = $.trim(newPassword.value);
//         if (value === "") {
//             npError.innerHTML = "请输入您的新密码";
//             return false;
//         }
//         if (!value.match(passwordPattern)) {
//             npError.innerHTML = "您的密码格式非法";
//             return false;
//         }
//         return true;
//     }
//
//     function checkConfirm() {
//         if (newPassword.value !== newPasswordConfirm.value) {
//             npcError.innerHTML = "您的两此新密码不一致";
//             return false;
//         }
//         return true;
//     }
// });