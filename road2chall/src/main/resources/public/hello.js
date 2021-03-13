$(document).ready(function() {

    $.get("http://localhost:8080/road2Chall/user/allUsers",function(resp){
    	$.each(resp, function(index, item) {
    		console.log(item);
    		$('#list-users').append('<li id="personne-'+item.id+'" class="list-group-item">'+item.name+' '+item.pwd+' <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>');
    	});
    });

    $('#list-users').delegate("li button", "click", function() {
    	console.log(this);
    	var elemid = $(this).parent().attr('id');

    	$.ajax({
		    type: "DELETE",
		    url: "http://localhost:8080/api/personnes/" + elemid.replace('personne-',''),
		    dataType: "json",
		    success: function(data){
		    	$("#"+elemid).remove();
		    }
		});
    });

    $('#addUserbtn').click(function(){
		var name = $('#nameuser-input').val();
		var pwd = $('#pwduser-input').val();

		$.ajax({
		    type: "PUT",
		    url: "http://localhost:8080/road2Chall/user/createUser",
		    data: JSON.stringify({ "name": name, "pwd" : pwd }),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		    	$('#list-users').append('<li id="personne-'+data.id+'" class="list-group-item">'+data.name+' '+data.pwd+' <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>');
		    }
		});


		$('#name-input').val('');
		$('#pwd-input') .val('');
		return false;
	});

    $.get("http://localhost:8080/road2Chall/team/allTeams",function(resp){
        $.each(resp, function(index, item) {
            console.log(item);
            $('#list-teams').append('<li id="team-'+item.id+'" class="list-group-item">'+item.name+' '+item.description+' <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>');
        });
    });

    $('#addTeambtn').click(function(){
        var name = $('#nameteam-input').val();
        var description = $('#descriptionteam-input').val();

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/road2Chall/team/createTeam",
            data: JSON.stringify({ "name": name, "description" : description }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#list-teams').append('<li id="team-'+data.id+'" class="list-group-item">'+data.name+' '+data.description+' '+data.code+'<button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>');
            }
        });


        $('#nameteam-input').val('');
        $('#descriptionteam-input').val('');
        return false;
    });
});