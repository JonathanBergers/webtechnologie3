/**
 * Created by falco on 23-10-15.
 */



$(document).ready(function(){
    checkUser(setContent);
});

function checkUser(callback){
    getFirstnameFromToken(callback);
}


//ajax shit
function getObjects(resource, callback, element) {
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
        dataType: "json"
    }).done(function(data) {
        console.log(data);
    }).fail(function(data) {
        console.log("ERROR");
    }).success(function(data){
        callback(data)
    });
};

function authreq(url, callback, token, type, data) {
    return $.ajax({
        contentType: "application/json",
        type: type,
        url: url,
        data: data,
        dataType: "json",
        headers: {
            "accessToken": token,
        }
    }).done(function(data){
        console.log("auth req resp = ");
        console.log(data);
    }).fail(function(data) {

    }).success(function(data){
        callback(data)
    });
}



//login shit
function logout(){
    localStorage.removeItem("NotflixToken");
    window.location.href = "/index.html";
}





function saveToken(token){
    localStorage.setItem("NotflixToken", token);
    console.log(token);
}

function getFirstnameFromToken(callback){
    $.postJSON("/api/login", "{NotflixToken: "+localStorage.getItem('NotflixToken')+"}", callback);
}










