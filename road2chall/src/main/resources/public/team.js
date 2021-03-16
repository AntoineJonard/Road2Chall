$(document).ready(function (){
    $("#user-name").text("Connecté en tant que : "+sessionStorage.getItem("user-name"));
    $("#deconnexion-button").click(function (){
        sessionStorage.clear();
        window.location.href = "connexion.html";
    });

    let infosChoice = $("#infos-choice");
    let gamesChoice = $("#games-choice");
    let choiceCopntainer = $("#choice-container");

    choiceCopntainer.load("teamInformations.html");
    infosChoice.toggleClass("selected");
    let infosSelected = true;

    infosChoice.click(function (){
        if (!infosSelected){
            choiceCopntainer.load("teamInformations.html");
            infosSelected = !infosSelected;
            infosChoice.toggleClass("selected");
            gamesChoice.toggleClass("selected");
        }
    })

    gamesChoice.click(function (){
        if (infosSelected){
            choiceCopntainer.load("teamGames.html");
            infosSelected = !infosSelected;
            gamesChoice.toggleClass("selected");
            infosChoice.toggleClass("selected");
        }
    })

})