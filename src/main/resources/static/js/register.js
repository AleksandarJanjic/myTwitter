$( document ).ready(function() {
    $("#registerForm").submit(function(event){
        event.preventDefault();
        let userName = $("input[name='username']").val();
        let password = $("input[name='password']").val();
        let posting = $.post('/register/newaccount', {userName: userName, password: password});
    });
});