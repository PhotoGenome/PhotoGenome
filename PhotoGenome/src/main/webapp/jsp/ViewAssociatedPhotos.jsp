<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJAX calls using Jquery in Servlet</title>

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
	
	if(sessionStorage.getItem("associationMode")=== "Filtered"){
		getFilteredAssociatedPhotos();
	} else if(sessionStorage.getItem("associationMode")=== "TextFiltered"){
		getTextFilteredAssociatedPhotos();
	}
	else{
		getAssociatedPhotos();	
	}
});

function getAssociatedPhotos(){
        		 $.getJSON(
        	        	  'getAssociatedPhotos.action' , {photoId:sessionStorage.getItem("photoId")},
        	        	  function(jsonAssociatedPhotos) {
        	        		  alert(jsonAssociatedPhotos);
        	             	 for (photo in jsonAssociatedPhotos.items) {
        	             			 $('#canvas').append('<div class="P_box"> <div photoId="'
        	                				  +jsonAssociatedPhotos.items[photo].photoId+
        	                				  '"class="Pclose_box"></div> <img photoId="'
        	                				  +jsonAssociatedPhotos.items[photo].photoId+
        	                				  '" class="Photo"  src="'+jsonAssociatedPhotos.items[photo].photoLink +'"> </img></div>');
        	             	}
        	          
        	      			  });
        	        	 return false;
};        	


function getFilteredAssociatedPhotos(){
        	        		 $.getJSON(
        	        	        	  'getFilteredAssociatedPhotos.action' , {photoId:sessionStorage.getItem("photoId"),photoCategoryIdList:sessionStorage.getItem("photoCategoryIdList"),regionCategoryIdList:sessionStorage.getItem("regionCategoryIdList")},
        	        	        	  function(jsonFilteredAssociatedPhotos) {
        	        	        		  alert(jsonFilteredAssociatedPhotos);
        	        	             	 for (photo in jsonFilteredAssociatedPhotos.items) {
        	        	             			 $('#canvas').append('<div class="P_box"> <div photoId="'
        	        	                				  +jsonFilteredAssociatedPhotos.items[photo].photoId+
        	        	                				  '"class="Pclose_box"></div> <img photoId="'
        	        	                				  +jsonFilteredAssociatedPhotos.items[photo].photoId+
        	        	                				  '" class="Photo"  src="'+jsonFilteredAssociatedPhotos.items[photo].photoLink +'"> </img></div>');
        	        	             	}
        	        	          
        	        	      			  });
        	        	        	 return false;
    };        	


function getTextFilteredAssociatedPhotos(){
		 $.getJSON(
	        	  'getTextFilteredAssociatedPhotos.action' , {photoId:sessionStorage.getItem("photoId"),photoCategoryList:sessionStorage.getItem("photoCategoryList"),regionCategoryList:sessionStorage.getItem("regionCategoryList")},
	        	  function(jsonFilteredAssociatedPhotos) {
	        		  alert(jsonFilteredAssociatedPhotos);
	             	 for (photo in jsonFilteredAssociatedPhotos.items) {
	             			 $('#canvas').append('<div class="P_box"> <div photoId="'
	                				  +jsonFilteredAssociatedPhotos.items[photo].photoId+
	                				  '"class="Pclose_box"></div> <img photoId="'
	                				  +jsonFilteredAssociatedPhotos.items[photo].photoId+
	                				  '" class="Photo"  src="'+jsonFilteredAssociatedPhotos.items[photo].photoLink +'"> </img></div>');
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