$(document).ready(function (){
    $("#user-name").text("Connecté en tant que : "+sessionStorage.getItem("user-name"));

    // Deconnexion
    $("#deconnexion-button").click(function (){
        // On vide les variables de connexion
        sessionStorage.clear();
        // On recharge la page de connexion
        window.location.href = "connexion.html";
    });

    // Bouton pour accéder aux informations d'une équipe
    let infosChoice = $("#infos-choice");
    // Bouton pour accéder aux games d'une équipe
    let gamesChoice = $("#games-choice");
    let choiceContainer = $("#choice-container");

    // On initialise sur la sous page d'informations
    choiceContainer.load("teamInformations.html");
    infosChoice.toggleClass("selected");
    let infosSelected = true;

    // Changement vers la page d'information si pas déjà selectionnée
    infosChoice.click(function (){
        if (!infosSelected){
            choiceContainer.load("teamInformations.html");
            infosSelected = !infosSelected;
            infosChoice.toggleClass("selected");
            gamesChoice.toggleClass("selected");
        }
    })

    // Changement vers la page des games si pas déjà selectionnée
    gamesChoice.click(function (){
        if (infosSelected){
            choiceContainer.load("teamGames.html");
            infosSelected = !infosSelected;
            gamesChoice.toggleClass("selected");
            infosChoice.toggleClass("selected");
        }
    })

})