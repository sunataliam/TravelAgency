function field_focus(field, email)
{
    if(field.value == email)
    {
        field.value = '';
    }
}

function field_blur_email(field, email)
{
    if(field.value == '')
    {
        field.value = email;
    }
}

function field_blur_password(field, password)
{
    if(field.value == '')
    {
        field.value = password;
    }
}

//Fade in dashboard box
$(document).ready(function(){
    $('.box').hide().fadeIn(1000);
});

//Stop click event
$('a').click(function(event){
    event.preventDefault();
});