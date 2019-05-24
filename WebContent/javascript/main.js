/*a00267386*/

var rootURL = "http://localhost:8080/Beauty/rest/beauty";

$(document).ready(function() {
	findAll();

	$('.modal').on('click', '#AddNew', function() {
		$("#beautyId").val("");
		$("#beautySerivce").val("");
		$("#beautyDuration").val("");
		$("#beautyVenue").val("");
		$("#beautyLocation").val("");
		$("#beautyPrice").val("");
		$("#beautyDescription").val("");
	});
	
	$('.modal').on('click', '#Save', function() {
		if($('#beautyId').val() == ''){
			createBeauty();
		} else {
			updateBeauty();
		}
		return false;
	});
	
	$('#Delete').click(function() {
		deleteBeauty();
	});
	
});	

var findAll = function() {
	$.ajax({
		type : 'Get',
		url : rootURL,
		dataType : "json",
		success : renderList
	});
};

var renderList= function (data) {
	output = '<div class="row">';
	$.each(data, function(index, beauty) {
	
		var img = "picture/" + beauty.picture;
		output += ('<div class="col-sm-6 col-md-4 col-lg-3"><div class="card"><img src='+ '"'+ img+ '"'+ 'height="190" width="225"class="center"><p>Service:'
					+ beauty.service+'</p><p>Venue:'+ beauty.venue+'</p><p>Location:'+ beauty.location+ '</p><p>Price:'+ beauty.price +'</p></div></div>');
	
		var beautyid = beauty.id;
		$('#table_body').append('<tr><td>' + beauty.service + '</td><td>' + beauty.venue+ '</td><td>' + beauty.location + '</td><td>'+ beauty.price + ' euro </td><td>'
							+ '<button type="button" id="beautyEdit'+beautyid+'"class="btn btn-info btn-lg" beautyId="'+beautyid+'" data-toggle="modal" data-target="#myModal">Edit</button></td></tr>');
		$(document).on("click",'#beautyEdit'+beautyid,function(){
			$.ajax({
				type : 'GET',
				url : rootURL + '/'+ beautyid,
				dataType : "json",
				success : function (data) {
					console.log('findById success: '+ data.name);
					$("#beautyId").val(beauty.id);
					$("#beautySerivce").val(beauty.service);
					$("#beautyDuration").val(beauty.duration);
					$("#beautyVenue").val(beauty.venue);
					$("#beautyLocation").val(beauty.location);
					$("#beautyPrice").val(beauty.price);
					$("#beautyDescription").val(beauty.description);	
				}
			})
		});
	});
	$('#table_id').DataTable();
	output += '</div>';
	$('#gridDiv').append(output);
}
	

//UPDATE by Id-PUT Method
var updateBeauty= function () {
	console.log('updateBeauty');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#beautyId').val(),
		dataType: "json",
		data: formToJSONupdate(),
		success: function(data, textStatus, jqXHR){
			alert("Update");
			location.reload(true);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateBeauty error: ' + textStatus);
		}
	})
};

//DELETE 
var deleteBeauty= function () {
	//console.log('deleteBeauty');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#beautyId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Beauty deleted successfully');
			location.reload(true);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteBeauty error: ' + textStatus);
		}
	});
}



var createBeauty= function() {
	console.log('createBeauty');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Beauty add');
			location.reload(true);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('createBeauty error: ' + textStatus);
		}
	});
}


var formToJSONupdate=function(){
	return JSON.stringify({
		"id": $('#beautyId').val(),
		"service": $('#beautySerivce').val(),
		"duration": $('#beautyDuration').val(),
		"venue": $('#beautyVenue').val(),
		"location": $('#beautyLocation').val(),
		"price": $('#beautyPrice').val(),
		"description": $('#beautyDescription').val(),
		"picture": ""
	});
};
	

var formToJSON=function () {
	return JSON.stringify({
		"service": $('#beautySerivce').val(),
		"duration": $('#beautyDuration').val(),
		"venue": $('#beautyVenue').val(),
		"location": $('#beautyLocation').val(),
		"price": $('#beautyPrice').val(),
		"description": $('#beautyDescription').val(),
		"picture": ""
	});
};

