            function submitCompanyProfile() {
                var formUrl = $("#company_info_form").attr("action");
                var companyName = $("#companyName")[0].value;
                var email = $("#email")[0].value;
                var website = $("#website")[0].value;
                var bulstat = $("#bulstat")[0].value;
                var description = $("#description_inp")[0].value;

                var data = {
                                name : escape(companyName),
                                email : escape(email),
                                website : escape(website),
                                bulstat : escape(bulstat),
                                description : escape(description)
                };
                
                if (areFeeldsEmpty(companyName, email, website, bulstat)) {
                    alert("corectly fill all fields!!!");
                    return;
                }

                $.ajax({
                    url: 'rest/company_profile/editCompanyProfile',
                    type: "POST",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(data)
                })
                .success(function(data) {
                    $("#company_info_form").attr("action", "company_profile.html");
                })
                .fail(function(data) {
                    $("#job_post_form").attr("action", "post_job.html");

                })
                .always(function() {
                    $("#company_info_form").submit();
                });
            }
            
            function isEmpty(str) {
                return (!str || 0 === str.length);
            }

            function areFeeldsEmpty(companyName, email, website, bulstat){
                if (isEmpty(companyName) || isEmpty(email) || isEmpty(website) || isEmpty(bulstat)) { 
                    return true;
                }
                return false;
            }