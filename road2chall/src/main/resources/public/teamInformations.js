$(document).ready(function (){
    let teamId = localStorage.getItem("team-id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/road2Chall/team/teams/"+teamId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (teamData){
            $("#team-name").text(teamData.name);
        }
    });

})