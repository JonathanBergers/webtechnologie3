/**
 * Created by falco on 25-10-15.
 */


function submitLogin() {

    // Stop form from submitting normally
    //event.preventDefault();

    // Get some values from elements on the page:
    var $form = $( "#loginForm" ),
        firstname = $form.find( "input[name='firstname']" ).val(),
        password = $form.find( "input[name='password']" ).val(),
        url = $form.attr( "action" );


    var posted = $.postJSON( url, "{firstname: "+firstname+",password: "+password+"}", login );
    console.log(posted);


}

function login(data){
    console.log("login");
    console.log(data);
    if(data.success){
        var token = data.messages.accesstoken;
        saveToken(token);
        window.location.href = "/users.html"
    } else {
        window.alert("wrong bitch!");
    }
}


//setContent shit
function setContent(data){
    if(data.success){
        window.location.href = "/users.html"
    } else {
        //do nothing
    }
}


