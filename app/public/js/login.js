(function($) {
    $(function() {
        $("#loginForm").submit(function(){
            $.ajax({
                url : "/validateLogin",
                type : "POST",
                data : { email : $("#email").val(), password : $("#password").val() },
                success : function(res) {

                    if(res.loginSuccess) {
                        alert("success");
                        window.location.href = "/shoppingCart";
                    } else {
                        $("#invalidLogin").show();
                    }
                }
            });
            return false;
        });
    });
})($);