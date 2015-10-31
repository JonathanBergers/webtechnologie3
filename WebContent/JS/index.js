/**
 * Created by falco on 31-10-15.
 */


function setContent(data){
    loggedUser = data.success;
    addPagesToMenu(loggedUser);
}

function addPagesToMenu(loggedIn){
    var menu = "#menuList";
    $(menu).append("<li class='active'><a href='/index.html'>Home</a></li><li><a href='/movies.html'>Movies</a></li>");
    if(loggedIn){
        $(menu).append("<li><a href='/users.html'>Users</a></li><li><a onclick='logout()'>Logout</a></li>");
    } else {
        $(menu).append("<li><a href='/registreer.html'>Register</a></li><li><a href='/login.html'>Login</a></li>");
    }
}