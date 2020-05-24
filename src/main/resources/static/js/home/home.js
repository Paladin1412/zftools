$(function () {

    // 接口地址
    var getSystemNavigationUrl = '/home_get_system_navigation';
    var getUserInfoUrl = '/home_get_user_info';

    getUserInfo();
    getSystemNavigationInfo();

    // 获取首页展示的用户信息
    function getUserInfo() {
        $.ajax({
            url: getUserInfoUrl,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                console.log('data = ' + JSON.stringify(data));
                if (data.message == "success") {
                    var user = JSON.parse(data.data);
                    var username = user.username;
                    $('.profile_info > h2').html(username);
                    $('#navbarDropdown').html(username);
                } else {
                    alert('获取session中的user信息失败了。' + data.data);
                }
            }
        })
    }

    // 获取首页系统、数据库导航信息
    function getSystemNavigationInfo() {
        $.ajax({
            url: getSystemNavigationUrl,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                console.log('data = ' + JSON.stringify(data));
                var sysList = eval(data.data);
                console.log("sysList = " + sysList)
                var tableHtml = '';
                var row = {};
                if (data.message == "success") {
                    console.log("sysList.length = " + sysList.length);
                    for (var i = 0; i < sysList.length; i++) {
                        row.sysId = sysList[i].sysId;
                        row.sysName = sysList[i].sysName;
                        row.tSysAddress = sysList[i].tSysAddress;
                        row.tSysIp = sysList[i].tSysIp;
                        row.qSysAddress = sysList[i].qSysAddress;
                        row.qSysIp = sysList[i].qSysIp;
                        row.dbCategory = sysList[i].database.dbCategory;
                        row.tDbIp = sysList[i].database.tDbIp;
                        row.tDbUsername = sysList[i].database.tDbUsername;
                        row.tDBPassword = sysList[i].database.tDBPassword;
                        row.qDbIp = sysList[i].database.qDbIp;
                        row.qDbUsername = sysList[i].database.qDbUsername;
                        row.qDbPassword = sysList[i].database.qDbPassword;
                        tableHtml = tableHtml + '<tr>' +
                            '<td>' + row.sysId + '</td>' +
                            '<td>' + row.sysName + '</td>' +
                            '<td><a href="' + row.tSysAddress + '">' + row.tSysAddress + '</a></td>' +
                            '<td>' + row.tSysIp + '</td>' +
                            '<td><a href="' + row.qSysAddress + '">' + row.qSysAddress + '</a></td>' +
                            '<td>' + row.qSysIp + '</td>' +
                            '<td>' + row.dbCategory + '</td>' +
                            '<td>' + row.tDbIp + '</td>' +
                            '<td>' + row.tDbUsername + '</td>' +
                            '<td>' + row.tDBPassword + '</td>' +
                            '<td>' + row.qDbIp + '</td>' +
                            '<td>' + row.qDbUsername + '</td>' +
                            '<td>' + row.qDbPassword + '</td>' +
                            '</tr>'
                    }
                    $(".os-table-tbody").html(tableHtml);
                } else {
                    tableHtml = tableHtml + '<tr><td colspan="13">获取数据异常</td></tr>';
                    $(".os-table-tbody").html(tableHtml);
                    alert('获取首页系统、数据库导航信息失败了。' + data.data);
                }
            }
        })
    }

})
