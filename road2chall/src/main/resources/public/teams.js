$(document).ready(function (){
    $("#user-name").text("Connecté en tant que : "+sessionStorage.getItem("user-name"));
    $("#deconnexion-button").click(function (){
        sessionStorage.clear();
        window.location.href = "connexion.html";
    })

    let userId = sessionStorage.getItem("user-id");

    $.get("http://localhost:8080/road2Chall/user/getTeams/users/"+userId,function(resp){
        $.each(resp, function(index, item) {
            console.log(item);
            appendToTeams(item);
        });
    });

    $('#add-team-btn').click(function(){
        var name = $('#nameteam-input').val();
        var description = $('#descriptionteam-input').val();

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/road2Chall/team/createTeam",
            data: JSON.stringify({ "name": name, "description" : description }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                appendToTeams(data);
            }
        });


        $('#nameteam-input').val('');
        $('#descriptionteam-input').val('');
        return false;
    });

    function appendToTeams(item){
        $('#list-teams').append('<li id="team-'+item.id+'" class="team-item">'+item.name+' - '+item.description+' <button class="remove-team-btn" data-title="Delete" data-toggle="modal" data-target="#delete" > X </button></li>');
    }
})