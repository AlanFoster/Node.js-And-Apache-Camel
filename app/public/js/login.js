(function($) {
    $(function() {
         $("#loginForm").submit(function(){
            $("#submitButton").button("loading");
            $.ajax({
                url : "/validateLogin",
                type : "POST",
                data : { email : $("#email").val(), password : $("#password").val() },
                success : function(res) {
                    if(res.loginSuccess) {
                        var redir = $("#redir").val();
                        window.location.href = redir;
                    } else {
                        $("#submitButton").button("reset");
                        $("#invalidLogin").show();
                    }
                }
            });
            return false;
        });
    });
})($);