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

    $("#inscriptionButton").click(function(){
        let name = $("#name").val();
        let pwd = $("#pwd").val();
        let pwdCheck = $("#pwd-check").val();
        if (name === '' || pwd === '' || pwd !== pwdCheck)
            alert("Valeurs incorrectes");
        else
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/road2Chall/user/createUser",
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
                        alert("Identifiant déjà utilisé ...");
                }
            })
    })
})