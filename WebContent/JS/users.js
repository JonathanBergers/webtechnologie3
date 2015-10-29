/**
 * Created by falco on 23-10-15.
 */




function displayUser(element, user){
    var firstname = user.firstname;
    var infix = user.infix;
    var lastname = user.lastname;

    $(element).html("<p>"+firstname+" "+infix+" "+lastname+"</p>");



    return;
}

function displayLoggedinUser(firstname){
    //setContentRatings();
    setContentUser(firstname, "#rowRight");

}

function displayRatings(ratings){

    for(rating in ratings){
        console.log(ratings[rating]);
        var stars = ratings[rating].stars;
        var title = ratings[rating].movie.titel;
        $("#rowLeft").append("<p> Film = "+title+" en rating = "+stars+"stars</p><br>");
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
        "                            <button type=\"button\" class=\"btn btn-default btn-lg\" onclick='setContentUser(\"" + firstname + "\",\"" + element + "\")'>\n" +
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
        displayLoggedinUser(data.messages.firstname);
        getObjects("/api/users", displayUsersInList, "#rowLeft");
    } else {
        window.location.href = "/login.html";
    }
}


function setContentUser(firstname, element){
    getObjects("/api/users?firstname="+firstname, displayUser, element);
}


function setContentRatings(){
    authreq("/api/ratings", displayRatings, localStorage.getItem("NotflixToken"), "GET");
}




