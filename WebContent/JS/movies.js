/**
 * Created by falco on 23-10-15.
 */

var loggedUser = false;
var firstnameUser = null;

function displayMoviesInList(element, movie) {

    var title = movie["titel"];
    var releaseDate = movie["releaseDate"];
    var discription = movie["discription"];
    var director = movie["director"];
    var length = movie["length"];


    $(element).append("<li onclick='setContentMovie(" + element + "','" + title + ")'>" + title + "</li>");
    return;
}
function addMovieItem(element, movie, width){



    var title = movie.titel;
    var releaseDate = movie.releaseDate;
    var description = movie.discription;
    var director = movie.director;
    var length = movie.length;

    width = typeof width !== 'undefined' ? width : 12;

    var movieItem =
        "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-lg\" onclick='setContentMovie(\"" + title + "\")'>\n" +
        "                                <span class=\"glyphicon glyphicon-play\" aria-hidden=\"true\"></span>"+ title +
        "                            </button>\n" +
        "                        </h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" + description +
        "                           <ul class=\"list-group\">\n" +
        createListItem("length : " + length) +
        createListItem("director : " + director) +


        "                        </ul>\n" +
        "                    </div>\n" +
        "                </div>" + "</div>";







    $(element).append(movieItem);

}

function createListItem(text){
    return "<li class=\"list-group-item\">" + text + "</li>"
}









function displayMovie(element, movie, width){

    var title = movie.titel;
    var releaseDate = movie.releaseDate;
    var description = movie.discription;
    var director = movie.director;
    var length = movie.length;
    var tt = movie.tt;
    var rating = movie.overAllRating;

    if(rating == -1){
        rating = "not rated"
    } else {
        rating += " stars";
    }

    width = typeof width !== 'undefined' ? width : 12;



    var movieItem =
        "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">"+title+"</h3>\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\" id='movieBody'>\n" + description +
        "                           <ul class=\"list-group\">\n" +
        createListItem("length : " + length) +
        createListItem("director : " + director) +
        createListItem("rating : " + rating) +
        "                        </ul>\n" +
        "                    </div>\n" +
        "                </div>" + "</div>";

    $(element).html(movieItem);

    addPoster(tt, "#movieBody");




    if(loggedUser){
        $("#movieBody").append("<form id='rateForm'></form>" );
        $form = $("#rateForm");
        $form.append("<input type='number' name='rating' min='1' value='10' max='10'>");
        $form.append("<button type='button' class='btn btn-default btn-lg' onclick='rateMovie(\"" + tt + "\")' >waardeer deze film</button>");
    }



    return;
}







//setContent shit
function setContent(data){
    loggedUser = data.success;
    addPagesToMenu(loggedUser);
    if(loggedUser){
        firstnameUser = data.messages.firstname;
    }
    getObjects("/api/movies", addMovieItem, "#rowLeft");

    //hardcoded Frozen, because Frozen
    setContentMovie("Frozen");
}

function addPagesToMenu(loggedIn){
    var menu = "#menuList";
    $(menu).append("<li><a href='/index.html'>Home</a></li><li class='active'><a href='/movies.html'>Movies</a></li>");
    if(loggedIn){
        $(menu).append("<li><a href='/users.html'>Users</a></li><li><a onclick='logout()'>Logout</a></li>");
    } else {
        $(menu).append("<li><a href='/registreer.html'>Register</a></li><li><a href='/login.html'>Login</a></li>");
    }
}

function setContentMovie(title){
    getObjects("/api/movies?titel="+title, displayMovie, "#rowRight");
}




function rateMovie(tt){
    var rating = $("#rateForm").find("input[name='rating']").val();

    authreq("/api/ratings", ratingReturn, localStorage.getItem("NotflixToken"), "PUT", "{'tt' : "+tt+", 'rating' : "+ rating +"}");
}

function ratingReturn(data){
    if(data.success){
        window.alert("Thanks for your rating");
    }
}

function addPoster(tt, element){

    $.ajax({
        "content-type": "application/json",
        type: "GET",
        url: "http://www.omdbapi.com/?i=tt"+tt+"&plot=short&r=json",
    }).done(function(data) {
        console.log(data);
    }).fail(function(data) {
        console.log("ERROR");
    }).success(function(data){
        $(element).append("<br><img src='"+data.Poster+"' class='img-responsive' alt='kon poster niet vinden....'>");
        console.log("YESSSS");
    });
}



