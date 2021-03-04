$(document).ready(function(){
    $("connexionButton").click(function(){
        $.post(
            'http://localhost:8080/road2Chall/user/connexion',
            {name:"Antoine", pwd: "pwd"},
            function(data){
              alert( "Data Loaded: " + data );
            }, "json"
        );
    })
})