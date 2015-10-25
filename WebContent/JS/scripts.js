/**
 * Created by falco on 23-10-15.
 */

function getObjects(element, resource, callback) {
    $.getJSON(resource, function (data) {

        $.each(data, function (i, objects) {
            for (object in objects) {
                console.log(objects[object]);
                callback(element, objects[object]);
            }
        });
    });
    return;
}


$.postJSON = function(url, data, callback) {
    return $.ajax({
        contentType: "application/json",
        type: "POST",
        url: url,
        data: data,
        success: callback,
        dataType: "json"
    }).done(function() {
        console.log("OK");
        console.log(data);
    }).fail(function(data) {
        console.log("ERROR");
        console.log(data);
    }).success(function(data){
        console.log(data);
        var token = data["messages"]["accesstoken"];
        localStorage.setItem("NotflixToken", token);
        console.log(token);
    });
};






function addUserItem(element, user, width) {


    var firstname = user["firstname"];
    var infix = user["infix"];
    var lastname = user["lastname"];
    var email = user["email"];
    if (typeof(width)==='undefined') width = 12;

    var useritem = "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-lg\">\n" +
        "                                <span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>"+ firstname + " " + infix + " " + lastname +
        "                            </button>\n" +
        "                        </h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" + email +
        "                    </div>\n" +
        "                </div>" + "</div>";
    $(element).append(useritem);

}


function createListItem(text){
    return "<li class=\"list-group-item\">" + text + "</li>"
}

