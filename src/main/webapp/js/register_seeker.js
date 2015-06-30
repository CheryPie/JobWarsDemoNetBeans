function register() {
    var username = $("#userName")[0].value;
    var password = $("#pass")[0].value;

    if (!isDataValid(password, username)) {
        return;
    }

    var data = {
        userName: username,
        password: password

    }

    $.ajax({
        url: 'rest/user/register_seeker',
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data)
    })
            .success(function (data) {
                $("#register_form").attr("action", "login.html");
                console.log("success");
            })
            .fail(function (data) {
                $("#register_form").attr("action", "register_seeker.html");
                console.log("failure");
            })
            .always(function () {
                $("#register_form").submit();
                console.log("submitted");
            });
}


