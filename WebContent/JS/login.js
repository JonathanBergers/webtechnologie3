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


    var posted = $.postJSON( url, "{firstname: "+firstname+",password: "+password+"}" );
    console.log(posted);
}
