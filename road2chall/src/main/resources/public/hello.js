$(document).ready(function() {

    $.get("http://localhost:8080/road2Chall/user/allUsers",function(resp){
    	$.each(resp, function(index, item) {
    		console.log(item);
    		$('#list').append('<li id="personne-'+item.id+'" class="list-group-item">'+item.name+' '+item.pwd+' <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>');
    	});
    });

    $('#list').delegate("li button", "click", function() {
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

    $('#addbtn').click(function(){
		var name = $('#name-input').val();
		var pwd = $('#pwd-input').val();

		$.ajax({
		    type: "PUT",
		    url: "http://localhost:8080/road2Chall/user/createUser",
		    data: JSON.stringify({ "name": name, "pwd" : pwd }),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
				console.log(data);
		    	console.log(data.id);
		    	$('#list').append('<li id="personne-'+data.id+'" class="list-group-item">'+data.name+' '+data.pwd+' <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>');
		    }
		});


		$('#name-input').val('');
		$('#pwd-input').val('');
		return false;
	});
});