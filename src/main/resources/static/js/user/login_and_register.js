$(function () {

    // 接口地址
    var loginUrl = '/user/check_login_info';
    var registerUrl = '/register';

    // 登录
    $('#login-submit').click(function () {
        var user = {};
        user.username = $('#login-username').val();
        user.password = $('#login-password').val();
        var formData = new FormData();
        formData.append('username', user.username);
        formData.append('password', user.password);
        $('#login-username').val('');
        $('#login-password').val('');
        console.log('user = ' + JSON.stringify(user));
        $.ajax({
            url: loginUrl,
            type: 'post',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log('data = ' + JSON.stringify(data));
                if (data.message == "success") {
                    window.location.href = 'home_page';
                } else {
                    alert('失败，' + data.data);
                }
            }
        });
    })

    // 注册
    $('#register-submit').click(function () {
        var user = {};
        user.username = $('#register-username').val();
        user.password = $('#register-password').val();
        var formData = new FormData();
        formData.append('username', user.username);
        formData.append('password', user.password);
        $('#register-username').val('');
        $('#register-password').val('');
        console.log('user = ' + JSON.stringify(user));
        $.ajax({
            url: registerUrl,
            type: 'post',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log('data = ' + JSON.stringify(data));
                if (data.message == "success") {
                    alert('注册成功，请登录！');
                    window.location.href = '/login#signin';
                } else {
                    alert('失败，' + data.data);
                }
            }
        });
    })

})
