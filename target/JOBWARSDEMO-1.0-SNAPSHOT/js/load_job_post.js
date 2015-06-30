$.ajax({
url : 'rest/post/get_company_posts',
type : "GET",
dataType : "json",
success : function(data) {
    console.log(JSON.stringify(data));
    console.dir(data);
    console.log(data.toString());
    renderTable(data);
    }
});

function renderTable(posts) {
    console.dir(posts);
    for (var i = 0; i < posts.length; i++) {
        drawPost(posts[i]);
    }
}

function drawPost(post) {
    var tags = "";
    JSON.stringify(post);
    for (var i = 0; i < post.jobPostSkillRels.length; i++) {
        tags += "<div class=\"post-tag\">" + post.jobPostSkillRels[i].skill.name + "</div>";
    }
    var row = $("<tr />");
    $("#post_table").append(row);
    row.append($("<td><div class=\"wrapper\"><div><h3 style=\"width:auto;color: #26a673;\">" + 
            post.header + "</h3></div><div>"+post.description+"</div></div><div>" +
            tags + "</div></td>"));
    console.log(post.header);
    console.log(post.description);
}
