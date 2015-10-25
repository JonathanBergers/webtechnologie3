/**
 * Created by falco on 23-10-15.
 */



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



    var title = movie["titel"];
    var releaseDate = movie["releaseDate"];
    var description = movie["discription"];
    var director = movie["director"];
    var length = movie["length"];

    width = typeof width !== 'undefined' ? width : 12;

    var movieItem =
        "<div class=\"col-md-" + width + "\"" +  ">" + "<div class=\"panel panel-default\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        <h3 class=\"panel-title\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-lg\" onclick='setContentMovie(\"" + element + "\",\"" + title + "\")'>\n" +
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
function displayMovie(element, movie){
    var title = movie["titel"];
    var releaseDate = movie["releaseDate"];
    var discription = movie["discription"];
    var director = movie["director"];
    var length = movie["length"];

    $(element).html("<p>"+title+" "+releaseDate+" "+discription+" "+ director+" "+ length+ "</p>");
    return;
}

function setContentMovies(element){
    $(element).html("");
    getObjects(element, "/api/movies", addMovieItem);
    return;
}

function setContentMovie(element, title){
    getObjects(element, "/api/movies?titel="+title, displayMovie);
    return;
}



