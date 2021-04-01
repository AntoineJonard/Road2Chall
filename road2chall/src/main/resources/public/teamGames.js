$(document).ready(function () {
    let teamId = localStorage.getItem("team-id");

    let nbGameDisplayed = 0;

    // Boutons de choix de l'issue de la game qui va être ajoutée
    $("#victory-choice").click(switchOutcome);
    $("#defeat-choice").click(switchOutcome);

    // Changement de l'issu de la game a ajouter
    function switchOutcome(){
        $("#victory-choice").toggleClass("selected-outcome-choice");
        $("#defeat-choice").toggleClass("selected-outcome-choice");
    }

    // Ajout d'une game
    $("#add-game").click(function (){
        if ($("#date-input").val() !== '')
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/road2Chall/team/addGame/teams/"+teamId,
                data: JSON.stringify({
                    "win": $("#victory-choice").hasClass("selected-outcome-choice"),
                    "date":$("#date-input").val(),
                    "note":$("#note-input").val()
                }),
                contentType: "application/json; charset=utf-8",
                success: function (game){
                    // On remet à 0 le compteur et on recharge toutes les games
                    // pour éviter l'affichage en double d'une game
                    nbGameDisplayed = 0;
                    $("#games-container").empty();
                    displayMoreGames();
                }
            });
        else
            alert("Date invalide");
    });

    // Affichage du nom de la team
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/road2Chall/team/teams/"+teamId,
        contentType: "application/json; charset=utf-8",
        success: function (teamData){
            $("#team-name").text(teamData.name);
        }
    });

    // Affichage des premières games
    displayMoreGames();

    $("#display-more").click(function (){
        displayMoreGames();
    })

    function displayMoreGames(){
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/road2Chall/team/getGames/teams/"+teamId,
            contentType: "application/json; charset=utf-8",
            success: function (games){
                // Boucle d'ajout de nouvelles games, limité par l'existant pour par a coup de +5
                for (let i = nbGameDisplayed, keys = Object.keys(games), l = keys.length ;
                     i < l && i < nbGameDisplayed+5 ;
                     i++)
                {
                    appendToGame(games[i]);
                }
                // On incrémente pour le prochain ajout de game
                nbGameDisplayed+=5;
            }
        });
    }

    // Ajout de la game en fonction des attributs
    function appendToGame(game){
        $("#games-container").append('<div id="game-'+game.id+'" class="game-item">' +
            '<img class="game-img" src="battle.png">' +
            '<div class="game-infos-container">' +
            '<p class="game-res">'+(game.win?"VICTOIRE":"DEFAITE")+'</p>' +
            '<p class="game-date">Le '+game.date+'</p>' +
            '<p class="game-note">Note de la game - '+game.note+'</p>' +
            '</div>' +
            '</div>');
    }
})