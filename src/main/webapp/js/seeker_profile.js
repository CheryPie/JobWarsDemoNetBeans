$.ajax({
    url: '/JOBWARSDEMO/LoginUserServlet',
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

function changeInput(filter) {
    cleanTables();
    if (filter != "") {
        $.ajax({
            url: '/JOBWARSDEMO/AutocompleteServlet?term=' + filter,
            type: "GET",
            dataType: "json",
            success: function (skills, textStatus, jqXHR) {
                drawSkillsTable(skills);
            }
        });
    }
}

function drawSkillsTable(skills) {
    for (var i = 0; i < skills.length; i++) {
        drawSkillRow(skills[i]);
    }
}

function drawSkillRow(rowData) {
    var row = $("<tr />")
    $("#autocomplete_it").append(row);
    row.append($("<td>" + rowData.name + "</td>"));
}

function cleanTables() {
    var skill_table = document.getElementById('autocomplete_it');
    if (skill_table != null) {
        while (skill_table.rows.length != 0) {
            skill_table.deleteRow(0);
        }
    }
}