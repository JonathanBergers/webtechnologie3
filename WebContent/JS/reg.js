/**
 * Created by falco on 24-10-15.
 */

    // Attach a submit handler to the form






function submitRegister() {

    // Stop form from submitting normally
    //event.preventDefault();

    // Get some values from elements on the page:
    var $form = $( "#registerForm" ),
        email = $form.find( "input[name='email']" ).val(),
        password = $form.find( "input[name='password']" ).val(),
        passwordConfirm = $form.find( "input[name='passwordConfirm']" ).val(),
        firstname = $form.find( "input[name='firstname']" ).val(),
        infix = $form.find( "input[name='infix']" ).val(),
        lastname = $form.find( "input[name='lastname']" ).val(),
        url = $form.attr( "action" );



    // Send the data using post


    var posted = $.postJSON( url, "{email: "+email+", passwordConfirm: "+passwordConfirm+", password: "+password+", firstname: "+firstname+", infix: "+infix+", lastname: "+lastname+"}", advance );
    console.log(posted);
}

function advance(data){
    if(!data.succes){
        var token = data.messages.accesstoken;
        saveToken(token);
        window.location.href = "/users.html"
    } else {
        window.alert("er ging iets mis");
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

