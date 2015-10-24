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


function displayUsersInList(element, user) {


    var firstname = user["firstname"];
    var infix = user["infix"];
    var lastname = user["lastname"];


    $(element).append("<li onclick=setContentUser('" + element + "','" + firstname + "')>" + firstname + "</li>");
    return;
}

function setContentUsers(element){
    $(element).html("");
    getObjects(element, "/api/users", displayUsersInList);
    return;
}

function setContentUser(element, firstname){
    getObjects(element, "/api/users?firstname="+firstname, displayUser);
}



