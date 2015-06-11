$.ajax({
    url: '/JOBWARSDEMO/LoginUserServlet',
    type: "GET",
    dataType: "json",
    success: function (data, textStatus, jqXHR) {
        drawDropdown(data.jobPosts);
    }
});

function drawDropdown(data) {
    $.each(data, function (i, option) {
        $('#sel').append(
                $('<option/>').attr("value", option.jobPostId).text(
                option.header));
    });
}

function loadAppliedCandidates() {
    var postId = document.getElementById('sel').value;
    $.ajax({
        url: '/JOBWARSDEMO/PostSeekerServlet?postId=' + postId,
        type: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            cleanSeekerTable();
            drawSeekers(data);
        }
    });
}

function drawSeekers(seekers) {
    for (var i = 0; i < seekers.length; i++) {
        drawSeeker(seekers[i]);
    }
}

function drawSeeker(seeker) {
    var row = $("<tr />")
    $("#seeker_table").append(row);
    var tags = "";
    for (var i = 0; i < seeker.jobSeekerSkillRels.length; i++) {
        tags += "<div class=\"post-tag\">" + seeker.jobSeekerSkillRels[i].skill.name + "</div>";
    }
    row.append($("<td><div>" + seeker.fullName + "</div><div>" + tags + "</div></td>"));
}

function cleanSeekerTable() {
    var seeker_table = document.getElementById("seeker_table");
    while (seeker_table.rows.length != 0) {
        seeker_table.deleteRow(0);
    }
}