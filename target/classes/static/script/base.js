/**
 * 获取项目根路径
 * @returns {string} http://localhost:6589/Blog_war_exploded   注意最后没有[/]
 */
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/'+ webName;
}