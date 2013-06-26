<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PhotoGenome - View Photos</title>

        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      <script src="jsonUtil.js"></script>
      
        <script>
</script>
        <style>
 
</style>
        <script>
        $(document).on('click','.Photo',function(){
        	 var photoParent= $(this).parent();
             var photo= photoParent.find('.Photo');
        	window.location = "EmbedInformationUI.jsp";
        	sessionStorage.setItem("photoId", photo.attr("photoId"));
			  });

        $(document).on('click','.Pclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                var photo= $(this).find('.Pclose_box');
                deletePhoto( photo.attr('photoId'));
                $(this).remove();
            });
        });

           
        </script>
        
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css" type="text/css" />
        <link rel="stylesheet" href="PG-Style.css" type="text/css" />
  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
  <style type="text/css" media="screen">
  body { background-color: white; font: 16px Helvetica, Arial; color: black; }
  #canvas { height: 500px; background: white; }
  </style>

<style id="jsbin-css">

</style>
</head>
<body designMode="on">

<form id="form1">
<table width="100%" bordercolor="black">
<tr><td width="70%" bordercolor="black" valign="top">
<div id="canvas" style="border:solid black;"></div>
</td><td valign="top">
</td>
</tr>
</table>


<script>

$(window).load(function () {
	getPhotosByUserId(1000); 
	
});

function getPhotoLink(){
	 $.getJSON(
        	  'getPhoto.action' , {photoId:$('#canvas').attr('photoId')},
        	  function(jsonPhoto) {
        		for (photo in jsonPhoto) {
        			$('#canvas').css('background-image', 'url('+photo.photolink+')');  
        			}
        	  });
        	 return false;
        	};
        	
function getPhotosByUserId(userId){
	  $.getJSON(
         'getPhotosByUserId.action' , {userId:userId},
         	  function(jsonPhotos) {
        	 var rowLength=0; 
        	 for (photo in jsonPhotos.items) {
        		   if(rowLength<4){
        			 $('#canvas').append('<div class="P_box"> <div photoId="'
           				  +jsonPhotos.items[photo].photoId+
           				  '"class="Pclose_box"></div> <img photoId="'
           				  +jsonPhotos.items[photo].photoId+
           				  '" class="Photo"  src="'+jsonPhotos.items[photo].photoLink +'"> </img></div>');
        			 rowLength++;
        	 } else {
        		 
        		 $('#canvas').append('<br>');
        		 rowLength=0;
        	 }
        	}
         });
      return false;
  };	

		function deletePhoto(photoId){
						$.getJSON(
					    	'deletePhoto.action' , {userId:1000,photoId:parseInt(photoId)},
					         	  function(jsonPhoto) {
					       	 });
					       return false;
					  };
</script>
</form>

</body>
</html>