/**
 * Created by falco on 23-10-15.
 */


var firstnameUser = "";

function displayUser(element, user, width){


    var firstname = user.firstname;
    var infix = user.infix;
    var lastname = user.lastname;
    var email = user.email;


    width = typeof width !== 'undefined' ? width : 12;
    var userItem =
        "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">"+firstname+"</h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\" id='movieBody'>\n" + firstname +" "+infix+" "+lastname+"<br>"+email+
        "                           <ul class=\"list-group\" id='ratingsList'>" +
        "                        </ul>" +
        "                    </div>\n" +
        "                </div>" + "</div>";

    $(element).html(userItem);

    if(firstname === firstnameUser){
        //show extra shit
        addRatings();
    }





    return;
}



function displayRatings(ratings){

    for(rating in ratings){
        console.log(ratings[rating]);
        var stars = ratings[rating].stars;
        var title = ratings[rating].movie.titel;
        $("#ratingsList").append("<li  class=\"list-group-item\">"+title+" : "+stars+" stars</li>");
    }



}


function displayUsersInList(element, user, width) {


    var firstname = user.firstname;
    var infix = user.infix;
    var lastname = user.lastname;
    var email = user.email;
    if (typeof(width)==='undefined') width = 12;

    var useritem = "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-lg\" onclick='setContentUser(\"" + firstname + "\")'>\n" +
        "                                <span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>"+ firstname + " " + infix + " " + lastname +
        "                            </button>\n" +
        "                        </h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" + email +
        "                    </div>\n" +
        "                </div>" + "</div>";
    $(element).append(useritem);



}




//setContent shit
function setContent(data){
    if(data.success){
        addPagesToMenu();
        firstnameUser = data.messages.firstname;
        setContentUser(firstnameUser, "#rowRight");
        getObjects("/api/users", displayUsersInList, "#rowLeft");
    } else {
        window.location.href = "/login.html";
    }
}

function addPagesToMenu(){
    $
    var menu = "#menuList";
    $(menu).append("<li><a href='/index.html'>Home</a></li><li><a href='/movies.html'>Movies</a></li><li class='active'><a href='/users.html'>Users</a></li><li><a onclick='logout()'>Logout</a></li>");
}


function setContentUser(firstname){
    getObjects("/api/users?firstname="+firstname, displayUser, "#rowRight");
}


function addRatings(){
    authreq("/api/ratings", displayRatings, localStorage.getItem("NotflixToken"), "GET");
}




