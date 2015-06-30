function logout() {
    $.ajax({
        url: 'rest/user/logout',
        type: "GET",
        dataType: "text"
    }).always(function (data) {
        window.location.replace("login.html");
    });
}


