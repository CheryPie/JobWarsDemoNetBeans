function forward() {
    $.ajax({
        url: 'rest/user',
        type: "GET",
        dataType: "text",
        statusCode: {
            200: function (data) {
                console.log(data);
                window.location.href = data;
            }}
    });
}

function login() {

    var username = $("#userName")[0].value;
    var password = $("#pass")[0].value;

    if (!isDataValid(password, username)) {
        return;
    }

    var data = {
        password: password,
        userName: username
    };

    console.log(data.password);
    console.log(data.userName);

    $.ajax({
        url: 'rest/user/login',
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        statusCode: {
            401: function () {
                console.log("Authentication failed");
            },
            200: function () {
                console.log("Authentication succeded");
                forward();
            }
        }
    });
}


$(document).mouseup(function (e)
{
    var container = $("#register");
    var x = $("#close");

    if (!container.is(e.target) // if the target of the click isn't the container...
            && container.has(e.target).length === 0) // ... nor a descendant of the container
    {
        container.fadeOut();
    }
});
function showRegButtons() {
    $("#register").fadeIn();
}

function fade() {
    var container = $("#register");
    container.fadeOut();
}