$(document).ready(function (){
    $("#user-name").text("Connecté en tant que : "+sessionStorage.getItem("user-name"));

    // Deconexion
    $("#deconnexion-button").click(function (){
        // Reset des variables de connexion
        sessionStorage.clear();
        // Redirection vers la page de connexion
        window.location.href = "connexion.html";
    })

    let userId = sessionStorage.getItem("user-id");

    // Initialisation des équipes
    $.get("http://localhost:8080/road2Chall/user/getTeams/users/"+userId,function(resp){
        $.each(resp, function(index, item) {
            appendToTeams(item);
        });
    });

    // Création d'une team puis ajout en fonction des données du formulaire
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

    // Liaison d'une team existant en fonction de son code fourni dans le fomrulaire
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

    // Ajout de la composante graphique team
    function appendToTeams(item){
        $('#list-teams').append('<li id="team-'+item.id+'" class="team-item"><div><p id="nom-team">'+item.name+' #'+item.code+'</p><p id="desc-team">'+item.description+'</p><a class="team-link">Plus d\'informations</a></div> <button class="remove-team-btn" data-title="Delete" data-toggle="modal" data-target="#delete" > X </button></li>');
        resetClickEvent();
    }

    // Bind de l'événement de clique sur une team
    function resetClickEvent(){

        // Reset du click
        $(".team-link").unbind();
        // On change de page en settant les attributs dont on aura besoin
        $(".team-link").click(function (){
            let teamId = $(this).closest("li").attr('id');
            teamId = teamId.replace("team-","");
            localStorage.setItem('team-id', teamId);
            window.location.href = "team.html";
        })

        // Appel du service de suppression d'une team (uniquement pour l'utilisateur) sur clique du bouton suppression
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