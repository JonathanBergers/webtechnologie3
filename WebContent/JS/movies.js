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








function displayMovie(element, movie, width){

    var title = movie.titel;
    var releaseDate = movie.releaseDate;
    var description = movie.discription;
    var director = movie.director;
    var length = movie.length;
    var tt = movie.tt;

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
        "                        </ul>\n" +
        "                    </div>\n" +
        "                </div>" + "</div>";

    $(element).html(movieItem);

    addPoster(tt, "#movieBody");




    if(loggedUser){
        $("#movieBody").append("<form id='rateForm'></form>" );
        $form = $("#rateForm");
        $form.append("<input type='number' name='rating' min='1' value='10' max='10'>");
        var rating = $form.find("input[name='rating']").val();
        console.log(rating);
        $form.append("<button type='button' class='btn btn-default btn-lg' onclick='rateMovie(\"" + tt + "\", \""+rating+"\")' >waardeer deze film</button>");
    }



    return;
}







//setContent shit
function setContent(data){
    loggedUser = data.success;
    if(loggedUser){
        firstnameUser = data.messages.firstname;
    }
    getObjects("/api/movies", addMovieItem, "#rowLeft");
}

function setContentMovie(title){
    getObjects("/api/movies?titel="+title, displayMovie, "#rowRight");
}




function rateMovie(tt, rating){
    authreq("/api/ratings", ratingReturn, localStorage.getItem("NotflixToken"), "PUT", "{'tt' : "+tt+", 'rating' : "+ rating +"}");
    window.alert("Rating "+ rating);
}

function ratingReturn(data){
    if(data.success){
        window.alert(data.messages.Rating);
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



