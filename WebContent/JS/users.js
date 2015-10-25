/**
 * Created by falco on 23-10-15.
 */




function displayUser(element, user){
    var firstname = user["firstname"];
    var infix = user["infix"];
    var lastname = user["lastname"];

    $(element).html("<p>"+firstname+" "+infix+" "+lastname+"</p>");



    return;
}


function displayUsersInList(element, user, width) {


    var firstname = user["firstname"];
    var infix = user["infix"];
    var lastname = user["lastname"];
    var email = user["email"];
    if (typeof(width)==='undefined') width = 12;

    var useritem = "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-lg\" onclick='setContentUser(\"" + element + "\",\"" + firstname + "\")'>\n" +
        "                                <span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>"+ firstname + " " + infix + " " + lastname +
        "                            </button>\n" +
        "                        </h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" + email +
        "                    </div>\n" +
        "                </div>" + "</div>";
    $(element).append(useritem);



}


function setContentUsers(element){
    $(element).html("");
    getObjects(element, "/api/users", displayUsersInList);
    return;
}

function setContentUser(element, firstname){
    getObjects(element, "/api/users?firstname="+firstname, displayUser);
}



