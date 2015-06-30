$.ajax({
    url: 'rest/seeker_profile/load_seeker_profile',
    type: "GET",
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    success: function (data, textStatus, jqXHR) {
        setCompanyValues(data);
    }
});

function setCompanyValues(seeker) {
    document.getElementById('fullName').value = seeker.fullName;
    document.getElementById('position').value = seeker.position;
    var tags = "";
    for (var i = 0; i < seeker.jobSeekerSkillRels.length; i++) {
        tags += "<div class=\"post-tag\">" + seeker.jobSeekerSkillRels[i].skill.name + "</div>";
    }
    $("#skill_field").append(tags);
}
