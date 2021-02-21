/**
 * [[登录校验函数]]
 * @param {DOM} form [[由页面传入，表单元素]]
 */
// function check(form) {
//     if (form == null || form == undefined) {
//         throw new Error("The form element shouldn't be null or undefined");
//     }
//     var error = document.getElementById("login_error");
//     return _validate(form.username, error, "请输入用户名") && _validate(form.password, error, "请输入密码");
// }

/**
 * [[校验一个元素是否为空]]
 * @param {[[DOM]]} element [[被校验的元素]]
 * @param {[[DOM]]} error   [[错误信息显示]]
 * @param {[[String]]} message [[错误信息]]
 * @returns {[[boolean]]} [[返回是否通过]]
 */
// function _validate(element, error, message) {
//     var ele_value = element.value.trim();
//     if (ele_value == "") {
//         error.innerHTML = message;
//         element.focus();
//         return false;
//     }
//     return true;
// }

/**
 * ajax请求校验验证码
 * @param {} element 验证码input元素
 * @param {} error 错误显示span
 * @param {} message错误提示信息
 */
// function _checkVerify(element, error, message) {
//     var value = element.value;
//     //是否成功
//     var hasSuccess = false;
//     $.ajax({
//         url: getRootPath()+"/login/verify",
//         data: "verify=" + value,
//         async: false,
//         dataType: "json",
//         success: function (json) {
//             if (json.code == 200) {
//                 hasSuccess = true;
//             } else {
//                 error.innerHTML = message;
//             }
//         }
//     });
//     return hasSuccess;
// }

/**
 * 换验证码
 * @param {验证码所在的img DOM元素} image
 */
// function image(image) {
//     // image.src = "image.jsp?" + Math.random();
//     image.src =getRootPath()+"/kaptcha.jpg";
// }
