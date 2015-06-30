$.ajax({
    url: 'rest/post/all_posts',
    type: "GET",
    dataType: "json",
    success: function (jobSeekerPosts, textStatus, jqXHR) {
        loadSeekerPosts(jobSeekerPosts);
    }
});

function loadSeekerPosts(appliedPosts) {
    $.ajax({
        url: 'rest/post/applied',
        type: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            drawPosts(data, appliedPosts);
        }
    });
}

function drawPosts(posts, appliedPosts) {
    for (var i = 0; i < posts.length; i++) {
        drawPost(posts[i], i, appliedPosts);
    }
}

function drawPost(post, index, appliedPosts) {
    var tags = "";
    // if (post.jobPostSkillRels != null) {
    for (var i = 0; i < post.jobPostSkillRels.length; i++) {
        tags += "<div class=\"post-tag\">" + post.jobPostSkillRels[i].skill.name + "</div>";
    }
    //   }
    var row = $("<tr />");
    $("#post_table").append(row);
    var apply = "";
    if (isExist(post.jobPostId, appliedPosts)) {
        apply = "<div><button class=\"red\" type=\"submit\">Вече е кандидатствано по обявата</button></div>";
    } else {
        apply = "<form method=\"post\" action=\"SeekerPostServlet\">" +
                "<input type=\"hidden\" name=\"post_index\" value=\"" + index + "\"/>" +
                "<button class=\"green\" type=\"submit\">Кандидатсвай</button>" +
                "</form>";
    }
    row.append($("<td><div class=\"wrapper\"><div><h3 style=\"width:auto;color: #26a673;\">" +
            post.header + "</h3></div><div>" + post.description + "</div></div><div>" +
            tags + apply + "</div><div></div></td>"));
}

function isExist(postId, appliedPosts) {
    for (var j = 0; j < appliedPosts.length; j++) {
        if (appliedPosts[j].jobPost.jobPostId == postId) {
            return true;
        }
    }
    return false;
}
