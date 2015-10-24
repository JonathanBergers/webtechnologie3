/**
 * Created by falco on 23-10-15.
 */



function displayMoviesInList(element, movie) {

    var title = movie["titel"];
    var releaseDate = movie["releaseDate"];
    var discription = movie["discription"];
    var director = movie["director"];
    var length = movie["length"];


    $(element).append("<li onclick=setContentMovie('" + element + "','" + title + "')>" + title + "</li>");
    return;
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
    getObjects(element, "/api/movies", displayMoviesInList);
    return;
}

function setContentMovie(element, title){
    getObjects(element, "/api/movies?titel="+title, displayMovie);
    return;
}



