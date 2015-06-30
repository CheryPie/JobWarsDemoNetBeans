function submitSeekerProfile() {
    var formUrl = $("#seeker_info_form").attr("action");
    var fullName = $("#fullName")[0].value;
    var position = $("#position")[0].value;
    var skills = $("#skill")[0].value;

    var data = {
                    fullName : escape(fullName),
                    position : escape(position)
    };

    if (areFeeldsEmpty(fullName, position)) {
        alert("corectly fill all fields!!!");
        return;
    }
    
    if (!isEmpty(escape(skills))) {
        $.ajax({
            url: 'rest/company_profile/editJobSeekerSkills',
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(escape(skills))
        })    
        .always(function() {
            $("#seeker_info_form").submit();
        });
    }

    $.ajax({
        url: 'rest/company_profile/editJobSeekerProfile',
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data)
    })
    .success(function(data) {
        $("#seeker_info_form").attr("action", "seeker_profile.html");
    })
    .fail(function(data) {
        $("#job_post_form").attr("action", "post_job.html");

    })
    .always(function() {
        $("#seeker_info_form").submit();
    });
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function areFeeldsEmpty(companyName, email){
    if (isEmpty(companyName) || isEmpty(email)) { 
        return true;
    }
    return false;
}