//$.ajax({
//    url: '/JOBWARSDEMO/LoginUserServlet',
//    type: "GET",
//    dataType: "json",
//    success: function (data, textStatus, jqXHR) {
//        setCompanyValues(data);
//    }
//});
//
//function setCompanyValues(data) {
//    document.getElementById('companyName').value = data.name;
//    document.getElementById('email').value = data.email;
//    document.getElementById('website').value = data.website;
//    document.getElementById('bulstat').value = data.bulstat;
//    document.getElementById('description_inp').value= data.description;
//}
            $.ajax({
                url: 'rest/company_profile/load_company_profile',
                type: "GET",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    setCompanyValues(data);
                }
            });

            function setCompanyValues(data) {
                document.getElementById('companyName').value = data.name;
                document.getElementById('email').value = data.email;
                document.getElementById('website').value = data.website;
                document.getElementById('bulstat').value = data.bulstat;
                document.getElementById('description_inp').value= data.description;
            }