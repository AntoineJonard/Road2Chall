$(document).ready(function () {
    let teamId = localStorage.getItem("team-id");

    $("#victory-choice").click(switchOutcome);
    $("#defeat-choice").click(switchOutcome);

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
                success: function (gameId){
                    console.log(gameId);
                    appendToGame(gameId);
                }
            });
        else
            alert("Date invalide");
    });

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/road2Chall/team/teams/"+teamId,
        contentType: "application/json; charset=utf-8",
        success: function (teamData){
            $("#team-name").text(teamData.name);
            $.each(teamData.games, function (index, gameId){
                console.log(gameId);
                appendToGame(gameId);
            });
        }
    });

    function switchOutcome(){
        $("#victory-choice").toggleClass("selected-outcome-choice");
        $("#defeat-choice").toggleClass("selected-outcome-choice");
    }

    function appendToGame(gameId){
        $.get("http://localhost:8080/road2Chall/game/games/"+gameId, function (game){
            $("#games-container").append('<div id="game-'+game.id+'" class="game-item">' +
                '<img class="game-img" src="battle.png">' +
                '<div class="game-infos-container">' +
                '<p class="game-res">'+(game.win?"VICTOIRE":"DEFAITE")+'</p>' +
                '<p class="game-date">Le '+game.date+'</p>' +
                '<p class="game-note">Note de la game - '+game.note+'</p>' +
                '</div>' +
                '</div>');
        });
    }
})