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
            success: function(teamData){
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/road2Chall/user/addTeam/personnes/"+userId+"/teams/"+teamData.code,
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                });
                appendToTeams(teamData);
            }
        });

        $('#nameteam-input').val('');
        $('#descriptionteam-input').val('');
        return false;
    });

    $('#link-team-btn').click(function(){
        var code = $('#code-input').val();

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/road2Chall/user/addTeam/personnes/"+userId+"/teams/"+code,
            success: function(teamData){
                if (teamData == null)
                    alert("Code non reconnu");
                else
                    appendToTeams(teamData);
            }
        });

        $('#code-input').val('');
        return false;
    });

    function appendToTeams(item){
        $('#list-teams').append('<li id="team-'+item.id+'" class="team-item"><div><p id="nom-team">'+item.name+'</p><p id="desc-team">'+item.description+'</p><a class="team-link">Plus d\'informations</a></div> <button class="remove-team-btn" data-title="Delete" data-toggle="modal" data-target="#delete" > X </button></li>');
        resetClickEvent();
    }

    function resetClickEvent(){
        $(".team-link").click(function (){
            let teamId = $(this).closest("li").attr('id');
            teamId = teamId.replace("team-","");
            localStorage.setItem('team-id', teamId);
            window.location.href = "team.html";
        })

        $(".remove-team-btn").click(function (){
            let teamId = $(this).closest("li").attr('id');
            teamId = teamId.replace("team-","");

            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/road2Chall/user/removeTeam/personnes/"+userId+"/teams/"+teamId,
                success: function(teamData){
                    if (teamData != null)
                        $('#team-'+teamData.id).remove();
                    else
                        alert("Erreur lors du détachement");
                }
            });
        })
    }
})