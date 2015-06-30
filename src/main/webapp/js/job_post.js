function submitPost() {
    var header = $("#header")[0].value;
    var description = $("#description")[0].value;

    var data = {
        header: escape(header),
        description: escape(description),
        company: null,
        jobPostSkillRels: [],
        jobSeekerPosts: []

    };

    $.ajax({
        url: 'rest/post/submitPost',
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data)
    })
            .success(function (data) {
                $("#job_post_form").attr("action", "company_page.html");
            })
            .fail(function (data) {
                $("#job_post_form").attr("action", "post_job.html");

            })
            .always(function () {
                $("#job_post_form").submit();
            });
}