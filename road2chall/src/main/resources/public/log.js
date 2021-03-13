$(document).ready(function(){

    $("#connexionButton").click(function(){
        let name = $("#name").val();
        let pwd = $("#pwd").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/road2Chall/user/connexion",
            data: JSON.stringify({"name":name, "pwd": pwd}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                sessionStorage.setItem('user-name',name);
                sessionStorage.setItem('user-id', data.id);
                window.location.href = "teams.html";
            },
            error: function (httpObj, textStatus){
                if (httpObj.status !== 200)
                    alert("identifiants ou mot de passe incorrect");
            }
        })
    })
})