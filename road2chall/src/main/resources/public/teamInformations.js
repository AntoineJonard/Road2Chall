$(document).ready(function (){
    let teamId = localStorage.getItem("team-id");
    let memberCpt = 0;

    $("#close-button").click(function (){
        $("#opaque").toggleClass("invisible");
        $("#modal").toggleClass("invisible");
    });

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/road2Chall/team/teams/"+teamId,
        contentType: "application/json; charset=utf-8",
        success: function (teamData){
            $("#team-name").text(teamData.name);
            $.each(teamData.members, function (index, member){
                appendToMembers(member);
            });
            $.each(teamData.notes, function (index, note){
                appendToNotes(note);
            });
        }
    });

    $("#add-member").click(function (){
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/road2Chall/team/addMember/teams/"+teamId,
            data: JSON.stringify({"name":$("#member-input").val()}),
            contentType: "application/json; charset=utf-8",
            success: function (member){
                appendToMembers(member);
            }
        });
    });

    $("#add-note").click(function (){
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/road2Chall/team/addNote/teams/"+teamId,
            data: JSON.stringify({"note":$("#note-input").val()}),
            contentType: "application/json; charset=utf-8",
            success: function (note){
                appendToNotes(note);
            }
        });
    });

    function appendToMembers(name){
        $("#team-members").append('<div id="member-'+name+'" class="member-item"><img class="member-img" src="member-icon.png"><div class="member-infos-container"><p class="member-name">'+name+'</p><p class="member-more">plus d\'informations</p></div><img src="delete.png" class="remove-member-btn"></div>');
        $(".member-more").unbind();
        $(".member-more").click(function (){
           $("#opaque").toggleClass("invisible");
           $("#modal").toggleClass("invisible");
           let name = $(this).closest(".member-item").attr("id").replace("member-","");
           $.ajax({
               type: "GET",
               url: "http://localhost:8080/road2Chall/call/summoner/"+name,
               success: function (stats){
                   $("#modal-content").empty();
                   $("#modal-content").append('<h2>'+name+'</h2>');
                   $.each(stats, function (index, line){
                       $("#modal-content").append('<p>'+line.queueType+' : '+line.tier+' '+line.rank+', '+line.leaguePoints+' lp</p>' +
                           '<p> Pourcentage de victoire : '+Math.round((line.wins)/(line.wins+line.losses)*1000)/10+'% ('+line.wins+' wins et '+line.losses+' looses)</p>');
                   });
               },
               error: function (httpObj, textStatus){
                   if (httpObj.status !== 200){
                       $("#modal-content").empty();
                       $("#modal-content").append('<p>Service temporairement indisponible ...</p>');
                   }
               }
           });
        });
        $(".remove-member-btn").click(function (){
            let memberName = $(this).closest(".member-item").attr("id").replace("member-", "");
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/road2Chall/team/removeMember/teams/"+teamId,
                data: JSON.stringify({"name": memberName}),
                contentType: "application/json; charset=utf-8",
                success: function (member){
                    if (member != null)
                        $('#member-'+member).remove();
                    else
                        alert("Erreur lors de la suppression du membre");
                }
            });
        });
    }

    function appendToNotes(note){
        $("#team-notes").append('<div class="team-note"><p>'+note+'</p></div>');
    }
})