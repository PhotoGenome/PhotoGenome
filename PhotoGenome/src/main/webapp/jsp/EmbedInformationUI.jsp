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
        var PRadio=null;
        var RRadio=null;
        $(document).on('click','.Rclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
            	 var region= $(this).find('.Rclose_box');
            	 deletePhotoRegion( region.attr('regionId'));
                      	$(this).remove();
            });
        });

        $(document).on('click','.PCclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                var comment= $(this).find('.PCclose_box');
                deletePhotoComment( comment.attr('photoCommentId'));
                $(this).remove();
            });
        });

        $(document).on('click','.PCedit_box',function(){
            var commentParent= $(this).parent();
            var comment= commentParent.find('.PCcomment_box');
            comment.attr("contentEditable","true");
            comment.focus();
            return false;
        });
        $(document).on('click','.PCsubmit_box',function(){
            var commentParent= $(this).parent().parent();
            var comment= commentParent.find('.PCcomment_box');
            var photoCommentText=comment.text();
            editPhotoComment(photoCommentText.trim(),comment.attr('photoCommentId'));
            return false;
        });
        $(document).on('click','.RCclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                  var comment= $(this).find('.RCclose_box');
                  deleteRegionComment( comment.attr('regionCommentId'));
                  $(this).remove();
                  
            });
        });
        $(document).on('click','.PCatFilterSelect_box',function(){
            $(this).addClass('PCatFilterSelected_box').removeClass('PCatFilterSelect_box');
         
        });
       
        $(document).on('click','.PCatFilterSelected_box',function(){
            $(this).addClass('PCatFilterSelect_box').removeClass('PCatFilterSelected_box');
         
        });
        $(document).on('click','.RCatFilterSelect_box',function(){
            $(this).addClass('RCatFilterSelected_box').removeClass('RCatFilterSelect_box');
         
        });
       
        $(document).on('click','.RCatFilterSelected_box',function(){
            $(this).addClass('RCatFilterSelect_box').removeClass('RCatFilterSelected_box');
         
        });
        $(document).on('click','.PCFilterSelect_box',function(){
            $(this).addClass('PCFilterSelected_box').removeClass('PCFilterSelect_box');
         
        });
       
        $(document).on('click','.PCFilterSelected_box',function(){
            $(this).addClass('PCFilterSelect_box').removeClass('PCFilterSelected_box');
         
        });
        $(document).on('click','.RCFilterSelect_box',function(){
            $(this).addClass('RCFilterSelected_box').removeClass('RCFilterSelect_box');
         
        });
       
        $(document).on('click','.RCFilterSelected_box',function(){
            $(this).addClass('RCFilterSelect_box').removeClass('RCFilterSelected_box');
         
        });
       
        
        $(document).on('click','.RCedit_box',function(){
            var commentParent= $(this).parent();
            var comment= commentParent.find('.RCcomment_box');
            comment.attr("contentEditable","true");
            comment.focus();
            return false;
        });
        $(document).on('click','.RCsubmit_box',function(){
        	var commentParent= $(this).parent().parent();
            var comment= commentParent.find('.RCcomment_box');
            var regionCommentText=comment.text();
            editRegionComment(regionCommentText.trim(),comment.attr('regionCommentId'));
            return false;
        });
       
        $(document).on('click','.PCatclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                var category= $(this).find('.PCatclose_box');
                deletePhotoCategory( category.attr('photoCategoryId'));
                $(this).remove();
            });
        });
        $(document).on('click','.PCatedit_box',function(){
            var commentParent= $(this).parent();
            var comment= commentParent.find('.PCatcomment_box');
            comment.attr("contentEditable","true");
            comment.focus();
            return false;
        });
        $(document).on('click','.PCatsubmit_box',function(){
        	var categoryParent= $(this).parent().parent();
            var category= categoryParent.find('.PCatcomment_box');
            var photoCategoryText=category.text().split(':');
            editPhotoCategory(photoCategoryText[0].trim(),photoCategoryText[1].trim(),category.attr('photoCategoryId'));
            return false;
        });
       
        $(document).on('click','.RCatclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
            	   var category= $(this).find('.RCatclose_box');
                   deleteRegionCategory( category.attr('regionCategoryId'));
                     $(this).remove();
            });
        });
        $(document).on('click','.RCatedit_box',function(){
            var commentParent= $(this).parent();
            var comment= commentParent.find('.RCatcomment_box');
            comment.attr("contentEditable","true");
            comment.focus();
            return false;
        });
        $(document).on('click','.RCatsubmit_box',function(){
            var categoryParent= $(this).parent().parent();
            var category= categoryParent.find('.RCatcomment_box');
           var regionCategoryText=category.text().split(':');
            editRegionCategory(regionCategoryText[0].trim(),regionCategoryText[1].trim(),category.attr('regionCategoryId'));
            return false;
        });
       
            $(document).ready(function() {                        
              
            	  $('#viewAssociatedPhotos').click(function(event) {  
            		  
            		  var allselectedPCat='';
            		  var allselectedPCatT='';
            		    $('.PCatFilterSelected_box').each(function(){
            			  // alert($( this ).attr('photoCategoryId'));
            			  var categoryParent= $(this).parent();
           				 var category= categoryParent.find('.PCatcomment_box');
            			var photoCategoryText=category.text().split(':');
            			allselectedPCatT= allselectedPCatT + photoCategoryText[0].replace(/ /g,'')+photoCategoryText[1].replace(/ /g,'')+' ';
                    	allselectedPCat=allselectedPCat + $( this ).attr('photoCategoryId')+' ';
            		      });
            		    
            		    /*var allselectedPC='';
            		    $('.PCFilterSelected_box').each(function(){
            			  // alert($( this ).attr('photoCommentId'));
            			   allselectedPC=allselectedPC + $( this ).attr('photoCommentId')+'|';
            		      });
            		    alert('PC:'+allselectedPC);
            		    */
            		    
            		    var allselectedRCat='';
            		    var allselectedRCatT='';
                		    $('.RCatFilterSelected_box').each(function(){
                		    	var categoryParent= $(this).parent().parent();
                	            var category= categoryParent.find('.RCatcomment_box');
                	           var regionCategoryText=category.text().split(':');
                	           allselectedRCatT= allselectedRCatT + regionCategoryText[0].replace(/ /g,'')+regionCategoryText[1].replace(/ /g,'')+' ';
                	            allselectedRCat=allselectedRCat + $( this ).attr('regionCategoryId')+' ';
            		      });
            		   /*var allselectedRC='';
            		    $('.RCFilterSelected_box').each(function(){
            			  // alert($( this ).attr('regionCommentId'));
            			   allselectedRC=allselectedRC + $( this ).attr('regionCommentId')+'|';
            		      });
            		    alert('RC:'+allselectedRC);
            		    */
            		    
            		   /* //based on Ids
            		    if(allselectedPCat==='' && allselectedRCat===''){
            		    	getAssociatedPhotos();
            		    } else 
            		    { 
            		    	getFilteredAssociatedPhotos(allselectedPCat.trim(),allselectedRCat.trim());
            		    }
            		    */
            		    
            		  	//based on Texts
            		    if(allselectedPCatT==='' && allselectedRCatT===''){
            		    	getAssociatedPhotos();
            		    } else 
            		    { 
            		    	getFilteredAssociatedPhotos(allselectedPCatT.trim(),allselectedRCatT.trim());
            		    }
            	  });
            	$('#submitRCat').click(function(event) {  
                	if($('input[name=RCategoryType]:checked', '#form1').val()==='Custom')
                	{
                		var regionCategories=$('#txtRegionCategories').val();
                    	var regionCategoryText=$('#txtRegionCategoryText').val();
                        
                    	addRegionCategory(regionCategories.trim(),regionCategoryText.trim());
                	} else if($('input[name=RCategoryType]:checked', '#form1').val()==='Predefined')
                    {
                		var regionCategories=$('#cbRegionCategories').val();
                    	var regionCategoryText=$('#txtPRegionCategoryText').val();
                    
                    	addRegionCategory(regionCategories.trim(),regionCategoryText.trim());	
                    }	 
                    
                   $('#txtRegionCategories').text('');
                 
                });
                $('#submitRCom').click(function(event) {  
                    var regionComments=$('#txtRegionComments').val();
                    addRegionComment(regionComments.trim());
                	$('#txtRegionCategories').text('');
                });
                $('#submitPCat').click(function(event) {  
                	
                	if($('input[name=PCategoryType]:checked', '#form1').val()==='Custom')
                	{
                		var photoCategories=$('#txtPhotoCategories').val();
                    	var photoCategoryText=$('#txtPhotoCategoryText').val();
                    	addPhotoCategory(photoCategories.trim(),photoCategoryText.trim());
                	} else if($('input[name=PCategoryType]:checked', '#form1').val()==='Predefined')
                    {
                		var photoCategories=$('#cbPhotoCategories').val();
                    	var photoCategoryText=$('#txtPPhotoCategoryText').val();
                    	addPhotoCategory(photoCategories.trim(),photoCategoryText.trim());	
                    }
                	$('#txtPhotoCategories').text('');
                 
                });
                $('#submitPCom').click(function(event) {  
                    var photoComments=$('#txtPhotoComments').val();
                    addPhotoComment(photoComments.trim());    
                    $('#txtPhotoCategories').text('');
                });
                
              	$("input[name='RCategoryType']").click( function( event )
                 {
              		if($(this).val()==='Custom')
                	{
          		    $('#txtRegionCategories').attr('disabled', false).focus();
          		  	$('#txtRegionCategoryText').attr('disabled', false).focus();
          			$('#txtPRegionCategoryText').attr('disabled', true);
      		    	$('#cbRegionCategories').attr('disabled', true);
        		    
                	} else if($(this).val()==='Predefined') {
          		    	$('#txtPRegionCategoryText').attr('disabled', false).focus();
          		    	$('#cbRegionCategories').attr('disabled', false).focus();
          		    	
          		      $('#txtRegionCategories').attr('disabled', true);
            		  	$('#txtRegionCategoryText').attr('disabled', true);
          		    		  
                	  }
                 });
              	$("input[name='PCategoryType']").click( function( event )
                        {
                     		if($(this).val()==='Custom')
                       	{
                 		    $('#txtPhotoCategories').attr('disabled', false).focus();
                 		  	$('#txtPhotoCategoryText').attr('disabled', false).focus();
                 			$('#txtPPhotoCategoryText').attr('disabled', true);
             		    	$('#cbPhotoCategories').attr('disabled', true);
               		    
                       	} else if($(this).val()==='Predefined') {
                 		    	$('#txtPPhotoCategoryText').attr('disabled', false).focus();
                 		    	$('#cbPhotoCategories').attr('disabled', false).focus();
                 		    	
                 		      $('#txtPhotoCategories').attr('disabled', true);
                   		  	$('#txtPhotoCategoryText').attr('disabled', true);
                 		    		  
                       	  }
                        });
                       		
                   
            });
                 
            $(function getUserName() {
                alert(Session["UserName"]);
            });
      
        </script>
        
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css" type="text/css" />
        <link rel="stylesheet" href="PG-Style.css" type="text/css" />
  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
  <style type="text/css" media="screen">
  body { background-color: white; font: 16px Helvetica, Arial; color: black; }
  #canvas { height: 500px; width:780px; background: white; }
  </style>

<style id="jsbin-css">

</style>
</head>
<body designMode="on">
<form>
<a href="../jsp/Home.jsp">Home</a>
<a href="../jsp/Logout.jsp" id="logout" >Logout</a><br>
<!--  input type="button" id="submitLogout" value="Logout"/><br>-->
</form>
<form id="form1">
<div id="mainDiv">
<table width="100%" bordercolor="black">
<tr><td width="780px" bordercolor="black" valign="top">
<div id="canvas"  style="border:solid black;"><img  height="500px" width="780px" id="canvasImg"></div>
</td><td valign="top">
<table >
<tr><td >Current Region Categories</td></tr>
<tr><td><div id="regionCategories"></div> </td></tr>
<tr>
	<td><input type="radio" name="RCategoryType" value="Predefined"> Predefined categories </td></tr>
	<tr> <td>
<select id="cbRegionCategories" disabled=true>
		<option value="">Select one...</option>
		<option value="Time">Time</option>
		<option value="Date">Date</option>
		<option value="Place">Place</option>
		<option value="Event">Event</option>
		<option value="People">People</option>
		<option value="City">City</option>
		<option value="State">State</option>
		<option value="Country">Country</option>
		<option value="Zip">Zip</option>
		<option value="Building">Building</option>
		
	</select><textarea id="txtPRegionCategoryText" disabled=true rows="1" cols="20">
Enter values.  
</textarea></td></tr>
<tr><td> <input type="radio" name="RCategoryType" value="Custom"> Custom <br> <textarea id="txtRegionCategories" disabled=true rows="1" cols="50">
Enter Categories   
</textarea>
<textarea id="txtRegionCategoryText" disabled=true rows="1" cols="50">
Enter Values Here. 
</textarea></td></tr> 
<tr><td> <input type="button" id="submitRCat" value="Categories Submit"/>
</td></tr>
<tr><td>Current Region Comments</td></tr>
<tr><td><div id="regionComments"></div>
<textarea id="txtRegionComments" disabled=true  rows="1" cols="50">
Enter Comments Here. 
</textarea>
<input type="button" id="submitRCom" value="Comments Submit"/>
</td></tr>

</table>
</td>
</tr>
<tr><td><input type="button" id="stop" value="Stop Marking" />
<input type="button" id="start" value="Start Marking" />
<input type="button" id="viewAssociatedPhotos" value="View Associated Photos" />
</td><td rowspan="10"><div id="importedMetadata"></div></td></tr>
<tr><td>Photo Categories</td></tr>
<tr><td><div id="photoCategories"></div> </td></tr>
<tr>
	<td><input type="radio" name="PCategoryType" value="Predefined"> Predefined categories </td></tr>
	<tr> <td>
<select id="cbPhotoCategories" disabled=true>
		<option value="">Select one...</option>
		<option value="Time">Time</option>
		<option value="Date">Date</option>
		<option value="Place">Place</option>
		<option value="Event">Event</option>
		<option value="People">People</option>
		<option value="City">City</option>
		<option value="State">State</option>
		<option value="Country">Country</option>
		<option value="Zip">Zip</option>
		<option value="Building">Building</option>
		
	</select><textarea id="txtPPhotoCategoryText" disabled=true rows="1" cols="20">
Enter values.  
</textarea></td></tr>
<tr><td> <input type="radio" name="PCategoryType" value="Custom"> Custom <br> <textarea id="txtPhotoCategories" disabled=true rows="1" cols="50">
Enter Categories   
</textarea>
<textarea id="txtPhotoCategoryText" disabled=true rows="1" cols="50">
Enter Values Here. 
</textarea></td></tr> 
<tr><td> <input type="button" id="submitPCat" value="Categories Submit"/>
</td></tr>
	
<tr><td>Photo Comments</td></tr>
<tr><td><div id="photoComments">
</div>
<textarea id="txtPhotoComments" rows="1" cols="50">
Enter Comments Here. 
</textarea>
<input type="button" id="submitPCom" value="Comments Submit"/>
</td></tr>
</table>

</div>
<script>

$(window).load(function () {
		if(sessionStorage.length > 0){
			getPhotoIdFromQS();
			getRegionCoordinates();
			getPhotoComments(); 
			getPhotoCategories();	
			getImportedMetadataByPhotoId();		
	}
		else {
			window.location = "Login.jsp";
				
		}

});


function getPhotoIdFromQS(){
	getPhotoLink(sessionStorage.getItem("photoId"));
	
	
}

function getAssociatedPhotos(){
	window.location = "ViewAssociatedPhotos.jsp";
	sessionStorage.setItem("associationMode", "NotFiltered");
	sessionStorage.setItem("SearchPhotoId", sessionStorage.getItem("photoId"));
    
	return false;
    };

 function getFilteredAssociatedPhotoIds(allselectedPCat,allselectedRCat){
  window.location = "ViewAssociatedPhotos.jsp";
  sessionStorage.setItem("photoCategoryIdList", allselectedPCat);
  sessionStorage.setItem("regionCategoryIdList", allselectedRCat);
  sessionStorage.setItem("SearchPhotoId", sessionStorage.getItem("photoId"));
  sessionStorage.setItem("associationMode", "Filtered");
        	        	 return false;
        	        	};        	

function getFilteredAssociatedPhotos(allselectedPCat,allselectedRCat){
        window.location = "ViewAssociatedPhotos.jsp";
        sessionStorage.setItem("photoCategoryList", allselectedPCat);
        sessionStorage.setItem("regionCategoryList", allselectedRCat);
        sessionStorage.setItem("SearchPhotoId", sessionStorage.getItem("photoId"));
        sessionStorage.setItem("associationMode", "TextFiltered");
        	        		                	      	
        return false;
        };        	


function getPhotoLink(photoId){
       $.getJSON(
        	  'getPhoto.action' , {photoId:photoId},
        function(jsonPhoto) {
         $('#canvasImg').attr("src",jsonPhoto.items.photoLink);  
        $('#canvas').attr("photoId",jsonPhoto.items.photoId);  
        	});
        return false;
   };       	        	
   function getUserFirstName(userId){
	  var name;
	  
	  $.ajax({
		    type: 'GET',
		    url: 'getUserByUserId.action',
		    dataType: 'json',
		    success: function(jsonUser) { 
		    	name= jsonUser.items.userFirstName
		    },
		    data: {userId:userId},
		    async: false
		});
	 /* alert( $.getJSON(
        	  'getUserByUserId.action' , {userId:userId},
        	   function(jsonUser) {
        		  alert(jsonUser.items.userFirstName);
        		return  jsonUser.items.userFirstName;  
        	}));
	 */
	//  alert(name);
	 return name;
      };       	        	
 function getPhotoCategories(){
	
	 $.getJSON(
         	  'getPhotoCategories.action' , {photoId:sessionStorage.getItem("photoId")},
         	  function(jsonPhotoCategories) {
         		 	for (category in jsonPhotoCategories.items) {
         		 		$('#photoCategories').append('<div class="box"> <div photoCategoryId="'
         		 				+jsonPhotoCategories.items[category].photoCategoryId+
         		 				'" class="PCatFilterSelect_box"></div> <div photoCategoryId="'
         		 				+jsonPhotoCategories.items[category].photoCategoryId+
         		 				'" class="PCatedit_box"></div> <div photoCategoryId="'
         		 				+jsonPhotoCategories.items[category].photoCategoryId+
         		 				'"class="PCatclose_box"></div> <h6>'+getUserFirstName(jsonPhotoCategories.items[category].userId)+'</h6><table><tr><td valign="top" width="90%"><div  photoCategoryId="'
         		 				+jsonPhotoCategories.items[category].photoCategoryId+'" class="PCatcomment_box">'
         		 				+jsonPhotoCategories.items[category].photoCategoryName+':'+jsonPhotoCategories.items[category].photoCategoryText+
         		 				'</div></td><td valign="top" width="10%"><div photoCategoryId="'
         		 				+jsonPhotoCategories.items[category].photoCategoryId+
         		 				'" class="PCatsubmit_box"></div></td></tr></table></div>');
    	                 }
    	            	  });
         	 return false;
         	};	
      
function getRegionCategories(regionId){
	   
	$.getJSON(
    	'getRegionCategories.action' , {regionId:regionId},
         	  function(jsonRegionCategories) {
    		for (category in jsonRegionCategories.items) {
    		 	  $('#regionCategories').append('<div class="box"> <div regionCategoryId="'
   		 				+jsonRegionCategories.items[category].regionCategoryId+
 		 				'" class="RCatFilterSelect_box"></div> <div regionCategoryId="'
	         			  +jsonRegionCategories.items[category].regionCategoryId+
	         			  '" class="RCatedit_box"></div> <div regionCategoryId="'
	         			  +jsonRegionCategories.items[category].regionCategoryId+
	         			  '" class="RCatclose_box"></div> <h6>'+getUserFirstName(jsonRegionCategories.items[category].userId)+'</h6><table><tr><td valign="top" width="90%"><div regionCategoryId="'
	         			  +jsonRegionCategories.items[category].regionCategoryId+
	         			  '" class="RCatcomment_box">'
	         			  +jsonRegionCategories.items[category].categoryName+
	         			  ':'+jsonRegionCategories.items[category].regionCategoryText+
	         			  '</div></td><td valign="top" width="10%"><div regionCategoryId="'
	         			  +jsonRegionCategories.items[category].regionCatgoryId+
	         			  '"class="RCatsubmit_box"></div></td></tr></table></div>');
	         	  }
    		  });
       return false;
  };	
function getPhotoComments(){
	  $.getJSON(
         'getPhotoComments.action' , {photoId:sessionStorage.getItem("photoId")},
         	  function(jsonPhotoComments) {
        	  for (comment in jsonPhotoComments.items) {
        		  $('#photoComments').append('<div class="box"> <div photoCommentId="'
   		 				+jsonPhotoComments.items[comment].photoCommentId+
 		 				'" class="PCFilterSelect_box"></div> <div photoCommentId="'
        				  +jsonPhotoComments.items[comment].photoCommentId+
        				  '" class="PCedit_box"></div> <div photoCommentId="'
        				  +jsonPhotoComments.items[comment].photoCommentId+
        				  '"class="PCclose_box"></div> <h6>'+getUserFirstName(jsonPhotoComments.items[comment].userId)+'</h6><table><tr><td valign="top" width="90%"><div photoCommentId="'
        				  +jsonPhotoComments.items[comment].photoCommentId+
        				  '" class="PCcomment_box">'
        				  +jsonPhotoComments.items[comment].photoCommentText+
        				  '</div></td><td valign="top" width="10%"><div photoCommentId="'
        				  +jsonPhotoComments.items[comment].photoCommentId+
        				  '"class="PCsubmit_box"></div></td></tr></table></div>');
	         	  }
         });
      return false;
  };	

  function getRegionComments(regionId){
	  $.getJSON(
         'getRegionComments.action' , {regionId:regionId},
         	  function(jsonRegionComments) {
        	 for (comment in jsonRegionComments.items) {
	         	  $('#regionComments').append('<div class="box"> <div regionCommentId="'
	   		 				+jsonRegionComments.items[comment].regionCommentId+
	 		 				'" class="RCFilterSelect_box"></div> <div regionCommentId="'
	         			  +jsonRegionComments.items[comment].regionCommentId+
	         			  '" class="RCedit_box"></div><div regionCommentId="'
	         			  +jsonRegionComments.items[comment].regionCommentId+
	         			  '" class="RCclose_box"></div> <h6>'+getUserFirstName(jsonRegionComments.items[comment].userId)+'</h6><table><tr><td valign="top" width="90%"><div regionCommentId="'
	         			  +jsonRegionComments.items[comment].regionCommentId+
	         			  '" class="RCcomment_box">'
	         			  +jsonRegionComments.items[comment].regionCommentText+
	         			  '</div></td><td valign="top" width="10%"><div regionCommentId="'
	         			  +jsonRegionComments.items[comment].regionCommentId+
	         			  '"class="RCsubmit_box"></div></td></tr></table></div>');
	         	  }
         });
      return false;
  };

  function getRegionCoordinates(){
	  $.getJSON(
	    	'getRegionCoordinates.action' , {photoId:sessionStorage.getItem("photoId")},
	         	  function(jsonRegionCoordinates) {
	    		for (coordinate in jsonRegionCoordinates.items) {
	    		addBox(jsonRegionCoordinates.items[coordinate].regionX,
	    				jsonRegionCoordinates.items[coordinate].regionY,
	    				jsonRegionCoordinates.items[coordinate].width,
	    				jsonRegionCoordinates.items[coordinate].height,
	    				jsonRegionCoordinates.items[coordinate].regionId); 
	         	  }
	         });
	       return false;
	  };	
	  
	  	function getImportedMetadataByPhotoId(){
     		
    		 $.getJSON(
    	         	  'getImportedMetadataByPhotoId.action' , {photoId:sessionStorage.getItem("photoId")},
    	         	  function(jsonImportedMetadata) {
    	         		  	for (data in jsonImportedMetadata.items) {
    	         		 		$('#importedMetadata').append('<div class="box"> <h6>Imported Metadata</h6><table><tr><td valign="top" width="90%">'
    	         		 				+'<div importedMetadataId='+jsonImportedMetadata.items[data].importedMetadataId+
    	         		 				'" class="PCatcomment_box">'+jsonImportedMetadata.items[data].importedMetadata+
    	         		 				'</div></td><td valign="top" width="10%"></td></tr></table></div>');
    	    	                 }
    	    	            	  });
    	         	 return false;
    	         	};	
   
  function addPhotoCategory(photoCategoryName,photoCategortyText){
	 	
		 $.getJSON(
	         	  'addPhotoCategory.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),photoCategoryName:photoCategoryName,photoCategoryText:photoCategortyText},
	         	  function(jsonPhotoCategories) {
	         			$('#photoCategories').append('<div class="box"> <div photoCategoryId="'
         		 				+jsonPhotoCategories.items.photoCategoryId+
         		 				'" class="PCatFilterSelect_box"></div> <div photoCategoryId="'
         		 				+jsonPhotoCategories.items.photoCategoryId+
         		 				'" class="PCatedit_box"></div> <div photoCategoryId="'
         		 				+jsonPhotoCategories.items.photoCategoryId+
         		 				'"class="PCatclose_box"></div> <h6>'+getUserFirstName(jsonPhotoCategories.items.userId)+'</h6><table><tr><td valign="top" width="90%"><div photoCategoryId="'
         		 				+jsonPhotoCategories.items.photoCategoryId+
         		 				'" class="PCatcomment_box">'
         		 				+jsonPhotoCategories.items.photoCategoryName+':'+jsonPhotoCategories.items.photoCategoryText+
         		 				'</div></td><td valign="top" width="10%"><div photoCategoryId="'
         		 				+jsonPhotoCategories.items.photoCategoryId+
         		 				'" class="PCatsubmit_box"></div></td></tr></table></div>');
    	             
	         	  });
	         	 return false;
	         	};	
	      
	function addRegionCategory(categoryName,regionCategoryText){
		$.getJSON(
	    	'addRegionCategory.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionId:$('#submitRCat').attr('currentRegionId'),categoryName:categoryName,regionCategoryText:regionCategoryText},
	         	  function(jsonRegionCategories) {
	         	  	  $('#regionCategories').append('<div class="box"> <div regionCategoryId="'
	     		 				+jsonRegionCategories.items.regionCategorytId+
	     		 				'" class="RCatFilterSelect_box"></div> <div regionCategoryId="'+
		         			  jsonRegionCategories.items.regionCategorytId+
		         			  '" class="RCatedit_box"></div> <div regionCategoryId="'
		         			  +jsonRegionCategories.items.regionCategoryId+
		         			  '"class="RCatclose_box"></div> <h6>'+getUserFirstName(jsonRegionCategories.items.userId)+'</h6><table><tr><td valign="top" width="90%"><div regionCategoryId="'+
		         			  jsonRegionCategories.items.regionCategorytId+
		         			  '" class="RCatcomment_box">'
		         			  +jsonRegionCategories.items.categoryName+':'+jsonRegionCategories.items.regionCategoryText+
		         			  '</div></td><td valign="top" width="10%"><div regionCategoryId="'
		         			  +jsonRegionCategories.items.regionCatgoryId+
		         			  '"class="RCatsubmit_box"></div></td></tr></table></div>');
	         	  
	         });
	       return false;
	  };	
	function addPhotoComment(photoCommentText){
	      $.getJSON(
	         'addPhotoComment.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),photoCommentText:photoCommentText},
	         	  function(jsonPhotoComments) {
	        	
	        		  $('#photoComments').append('<div class="box"> <div photoCommentId="'
	     		 				+jsonPhotoComments.items.photoCommentId+
	     		 				'" class="PCFilterSelect_box"></div> <div photoCommentId="'+
	        				  jsonPhotoComments.items.photoCommentId+
	        				  '" class="PCedit_box"></div> <div photoCommentId="'
	        				  +jsonPhotoComments.items.photoCommentId+
	        				  '"class="PCclose_box"></div> <h6>'+getUserFirstName(jsonPhotoComments.items.userId)+'</h6><table><tr><td valign="top" width="90%"><div photoCommentId="'+
	        				  jsonPhotoComments.items.photoCommentId+
	        				  '" class="PCcomment_box">'
	        				  +jsonPhotoComments.items.photoCommentText+
	        				  '</div></td><td valign="top" width="10%"><div photoCommentId="'
	        				  +jsonPhotoComments.items.photoCommentId+
	        				  '"class="PCsubmit_box"></div></td></tr></table></div>');
	         	 
	         });
	      return false;
	  };	

	  function addRegionComment(regionCommentText){
		  
	      $.getJSON(
	         'addRegionComment.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionId:$('#submitRCom').attr('currentRegionId'),regionCommentText:regionCommentText},
	         	  function(jsonRegionComments) {
	         	    $('#regionComments').append('<div class="box"> <div regionCommentId="'
	   		 				+jsonRegionComments.items.regionCommentId+
	 		 				'" class="RCFilterSelect_box"></div><div regionCommentId="'+
	         				  jsonRegionComments.items.regionCommentId
	         				  +'" class="RCedit_box"></div><div regionCommentId="'+
	         				  jsonRegionComments.items.regionCommentId
	         				  +'"class="RCclose_box"></div> <h6>'+getUserFirstName(jsonRegionComments.items.userId)+'</h6><table><tr><td valign="top" width="90%"><div regionCommentId="'+
	         				  jsonRegionComments.items.regionCommentId
	         				  +'" class="RCcomment_box">'
	         				  +jsonRegionComments.items.regionCommentText+
	         				  '</div></td><td valign="top" width="10%"><div photoCommentId="'
	         				  +jsonRegionComments.items.regionCommentId
	         				  +'"class="RCsubmit_box"></div></td></tr></table></div>');
	         	  
	         });
	      return false;
	  };

	  function editPhotoCategory(photoCategoryName,photoCategortyText, photoCategoryId){
			 $.getJSON(
		         	  'editPhotoCategory.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),photoCategoryId:photoCategoryId,photoCategoryName:photoCategoryName,photoCategoryText:photoCategortyText},
		         	  function(jsonPhotoCategories) {
		         	  });
		         	 return false;
		         	};	
		      
		function editRegionCategory(categoryName,regionCategoryText,regionCategoryId){
			$.getJSON(
		    	'editRegionCategory.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionCategoryId:regionCategoryId,categoryName:categoryName,regionCategoryText:regionCategoryText},
		         	  function(jsonRegionCategories) {
		         });
		       return false;
		  };	
		function editPhotoComment(photoCommentText,photoCommentId){
		
		      $.getJSON(
		         'editPhotoComment.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),photoCommentId:photoCommentId,photoCommentText:photoCommentText},
		         	  function(jsonPhotoComments) {
		        	});
		      return false;
		  };	

		  function editRegionComment(regionCommentText,regionCommentId){
		      $.getJSON(
		         'editRegionComment.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionCommentId:regionCommentId,regionCommentText:regionCommentText},
		         	  function(jsonRegionComments) {
		         	  
		         });
		      return false;
		  };
		  

	  function deleteRegionCategory(regionCategoryId){
			$.getJSON(
		    	'deleteRegionCategory.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionCategoryId:parseInt(regionCategoryId)},
		         	  function(jsonRegionCategories) {
		       	 });
		       return false;
		  };
		  
		 function deleteRegionComment(regionCommentId){
				$.getJSON(
			    	'deleteRegionComment.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionCommentId:parseInt(regionCommentId)},
			         	  function(jsonRegionCategories) {
			       	 });
			       return false;
			  };
	  
			  function deletePhotoRegion(regionId){
				 	$.getJSON(
				    	'deletePhotoRegion.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),regionId:parseInt(regionId)},
				         	  function(jsonPhotoRegion) {
				       	 });
				       return false;
				  };
		  function deletePhotoCategory(photoCategoryId){
					$.getJSON(
				    	'deletePhotoCategory.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),photoCategoryId:parseInt(photoCategoryId)},
				         	  function(jsonRegionCategories) {
				       	 });
				       return false;
				  };
		function deletePhotoComment(photoCommentId){
						$.getJSON(
					    	'deletePhotoComment.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),photoCommentId:parseInt(photoCommentId)},
					         	  function(jsonRegionCategories) {
					       	 });
					       return false;
					  };
	function parseJSON(){
		  alert('hie1');
		  jsonRegionComments={"items":[{"photoDesc":"my description","photoId":10,"photoIsdeleted":null,"photoLink":null,"photoMetadatalink":null,"photoName":null,"photoOption1":null,"photoOption2":null,"photoOption3":null,"photoOption4":null,"photoOption5":null,"photoTimestamp":null,"userId":0},{"photoDesc":"my description 2","photoId":20,"photoIsdeleted":null,"photoLink":null,"photoMetadatalink":null,"photoName":null,"photoOption1":null,"photoOption2":null,"photoOption3":null,"photoOption4":null,"photoOption5":null,"photoTimestamp":null,"userId":0}]};
	         	  for (comment in jsonRegionComments.items) {
	         		  $('#regionComments').append('<div class="box"> <div commentId="'+jsonRegionComments.items[comment].photoDesc+'"class="close_box">X</div> <h6>'+getUserFirstName(sessionStorage.getItem("userId"))+'</h6><p>'+jsonRegionComments.items[comment].photoDesc+'</p></div>');
	         	  	}
	       
	      return false;
	  };
  
function addBox(x,y,w,h,regionId){
	var canvas = $('#canvas');
	 canvas.append('<div id="'+'regionx_' + x + '_y_' +y+'_w_' + w + '_h_' + h+'"></div>');
	 $('#'+'regionx_' + x + '_y_' +y+'_w_' + w + '_h_' + h).addClass('ui-boxer')
	     .css({ border: '2px solid white',
	             padding: '0.5em',
	             position: 'absolute',
	            'z-index': 100,
	             left: parseInt(x), top: parseInt(y),
	             width: parseInt(w), height: parseInt(h)})
	     .append('<div regionId="'+regionId+'" class="Rclose_box"></div> ')
		.click(function () {
			jQuery('#regionComments').html('');
			jQuery('#regionCategories').html('');
			$('#txtRegionCategories').attr('disabled', false).focus();
		    $('#txtRegionComments').attr('disabled', false).focus();
		    getRegionComments(regionId);
			getRegionCategories(regionId);
			$('#submitRCom').attr('currentRegionId', regionId);
			$('#submitRCat').attr('currentRegionId', regionId);
			$('.ui-boxer').each(function() {
				$(this).css({ border: '2px solid white', padding: '0.5em' });
			});
			$(this).css({ border: '2px solid red', padding: '0.5em' });
			

		});


};


$(function () {
    var div = $("#canvas").boxer();
    $("#stop").click(function () {
        div.boxer("tempDisable");      
    });
});

$(function () {
    var div = $("#canvas").boxer();
    $("#start").click(function () {
        div.boxer("tempEnable");
    });
});
// Boxer plugin
$.widget("ui.boxer", $.extend({}, $.ui.mouse, {

	_init: function() {
		this.element.addClass("ui-boxer");

		this.dragged = false;

		this._mouseInit();

		this.helper = $(document.createElement('div'))
			.css({border:'1px dotted red'})
			.addClass("ui-boxer-helper");

	},

	destroy: function() {
		this.element
			.removeClass("ui-boxer ui-boxer-disabled")
			.removeData("boxer")
			.unbind(".boxer");
		this._mouseDestroy();

		return this;
	},

		tempDisable: function() {
		this._mouseDestroy();
		return this;
	},
	
	tempEnable: function() {
		this._mouseInit();
		return this;
	},
	_mouseStart: function(event) {
		var self = this;

		this.opos = [event.pageX, event.pageY];

		if (this.options.disabled)
			return;

		var options = this.options;

		this._trigger("start", event);

		$(options.appendTo).append(this.helper);

		this.helper.css({
			"z-index": 100,
			"position": "absolute",
			"left": event.clientX,
			"top": event.clientY,
			"width": 0,
			"height": 0
		});
	},

	_mouseDrag: function(event) {
		var self = this;
		this.dragged = true;

		if (this.options.disabled)
			return;

		var options = this.options;

		var x1 = this.opos[0], y1 = this.opos[1], x2 = event.pageX, y2 = event.pageY;
		if (x1 > x2) { var tmp = x2; x2 = x1; x1 = tmp; }
		if (y1 > y2) { var tmp = y2; y2 = y1; y1 = tmp; }
		this.helper.css({left: x1, top: y1, width: x2-x1, height: y2-y1});
		
		this._trigger("drag", event);

		return false;
	},

	_mouseStop: function(event) {
		var self = this;

		this.dragged = false;

		var options = this.options;

		var clone = this.helper.clone()
			.removeClass('ui-boxer-helper').appendTo(this.element);

		this._trigger("stop", event, { box: clone });

		this.helper.remove();

		return false;
	}

}));
$.extend($.ui.boxer, {
	defaults: $.extend({}, $.ui.mouse.defaults, {
		appendTo: 'body',
		distance: 0
	})
});

// Using the boxer plugin
$('#canvas').boxer({
	stop: function(event, ui) {
		var offset = ui.box.offset();
		var regionId;
		$.getJSON(
		    	'addPhotoRegion.action' , {photoId:sessionStorage.getItem("photoId"),userId:sessionStorage.getItem("userId"),shapeId:1,regionX:parseInt(offset.left),regionY:parseInt(offset.top),width:parseInt(ui.box.width()),height:parseInt(ui.box.height())},
		         	  function(jsonRegionCoordinates) {
		    	    regionId=  jsonRegionCoordinates.items.regionId;
		    		ui.box.append('<div regionId="'+regionId+'" class="Rclose_box"></div> ');
		             	 
		         });
		
		ui.box.css({ border: '2px solid white', padding: '0.5em' });
		ui.box.addClass('ui-boxer');
		ui.box.attr('id', regionId);
	//	ui.box.append('<div regionId="'+regionId+'" class="Rclose_box"></div> ');
		ui.box.click(function () {
			jQuery('#regionComments').html('');
			jQuery('#regionCategories').html('');
			$('#txtRegionCategories').attr('disabled', false).focus();
		    $('#txtRegionComments').attr('disabled', false).focus();
		    getRegionComments(regionId);
			getRegionCategories(regionId);
			$('#submitRCom').attr('currentRegionId', regionId);
			$('#submitRCat').attr('currentRegionId', regionId);
			$('.ui-boxer').each(function() {
				$(this).css({ border: '2px solid white', padding: '0.5em' });
			});
			$(this).css({ border: '2px solid red', padding: '0.5em' });
			
			
		});
		
	}
});
</script>
</form>

</body>
</html>