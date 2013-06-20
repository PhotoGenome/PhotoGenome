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
                  $(this).remove();
            });
        });

        $(document).on('click','.PCclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
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
            var commentParent= $(this).parent();
            var comment= commentParent.find('.PCcomment_box');
            var photoCommentText=comment.text();
            addPhotoComment(photoCommentText);
            return false;
        });
        $(document).on('click','.RCclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                  $(this).remove();
            });
        });

        $(document).on('click','.PCatclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                  $(this).remove();
            });
        });

        $(document).on('click','.RCatclose_box',function(){
            $(this).parent().fadeTo(300,0,function(){
                  $(this).remove();
            });
        });

            $(document).ready(function() {                        
                $('#submitRCat').click(function(event) {  
                	if($("input[name='RCategoryType']").val()==='Custom')
                	{
                		var regionCategories=$('#txtRegionCategories').val();
                    	var regionCategoryText=$('#txtRegionCategoryText').val();
                    	addRegionCategory(regionCategories,regionCategoryText);
                	} else if($("input[name='RCategoryType']").val()==='Custom')
                    {
                		var regionCategories=$('#cbRegionCategories').val();
                    	var regionCategoryText=$('#txtPRegionCategoryText').val();
                    	addRegionCategory(regionCategories,regionCategoryText);	
                    }	 
                    
                   $('#txtRegionCategories').text('');
                 
                });
                $('#submitRCom').click(function(event) {  
                    var regionComments=$('#txtRegionComments').val();
                    addRegionComment(regionComments);
                	$('#txtRegionCategories').text('');
                });
                $('#submitPCat').click(function(event) {  
                	alert('I reached here'+$('input[name=PCategoryType]:checked', '#form1').val());
            		
                	if($('input[name=PCategoryType]:checked', '#form1').val()==='Custom')
                	{
                		var photoCategories=$('#txtPhotoCategories').val();
                    	var photoCategoryText=$('#txtPhotoCategoryText').val();
                    	addPhotoCategory(photoCategories,photoCategoryText);
                	} else if($('input[name=PCategoryType]:checked', '#form1').val()==='Predefined')
                    {
                		alert('I reached');
                		var photoCategories=$('#cbPhotoCategories').val();
                    	var photoCategoryText=$('#txtPPhotoCategoryText').val();
                    	alert('JQ'+photoCategories+':'+photoCategortyText);
                    	addPhotoCategory(photoCategories,photoCategoryText);	
                    }
                	$('#txtPhotoCategories').text('');
                 
                });
                $('#submitPCom').click(function(event) {  
                    var photoComments=$('#txtPhotoComments').val();
                    addPhotoComment(photoComments);    
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
  #canvas { height: 500px; background: white; }
  </style>

<style id="jsbin-css">

</style>
</head>
<body designMode="on">
<form id="form1">
<table width="100%" bordercolor="black">
<tr><td width="70%" bordercolor="black">
<div id="canvas" photoId="1" style="border:solid black; background-image:url('https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-ash3/942522_10151496380413710_188944828_n.jpg');"></div>
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
<input type="button" id="stop" value="Stop!" />
<input type="button" id="start" value="Start!" />


<script>

$(window).load(function () {
	getRegionCoordinates();
	getPhotoComments(); 
	getPhotoCategories();
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
        	
function getPhotoCategories(){
	
	 $.getJSON(
         	  'getPhotoCategories.action' , {photoId:$('#canvas').attr('photoId')},
         	  function(jsonPhotoCategories) {
         		 	for (category in jsonPhotoCategories.items) {
         		 		$('#photoCategories').append('<div class="box"> <div photoCategoryId="'+jsonPhotoCategories.items[category].photoCategoryId+'" class="PCatedit_box">e</div> <div photoCategoryId="'+jsonPhotoCategories.items[category].photoCategoryId+'"class="PCatclose_box">x</div> <h2>Apoorvi</h2><table><tr><td valign="top" width="90%"><div class="PCatcomment_box">'+jsonPhotoCategories.items[category].photoCategoryName+':'+jsonPhotoCategories.items[category].photoCategoryText+'</div></td><td valign="top" width="10%"><div photoCategoryId="'+jsonPhotoCategories.items[category].photoCategoryId+'" class="PCatsubmit_box">s</div></td></tr></table></div>');
    	                 }
    	            	  });
         	 return false;
         	};	
      
function getRegionCategories(regionId){
	  alert(regionId);
	    
	$.getJSON(
    	'getRegionCategories.action' , {regionId:regionId},
         	  function(jsonRegionCategories) {
    		alert('hi');
    		  for (category in jsonRegionCategories.items) {
	         	  $('#regionCategories').append('<div class="box"> <div regionCategoryId="'+jsonRegionCategory.items[category].regionCategorytId+'" class="RCatedit_box">e</div> <div regionCategoryId="'+jsonRegionCategories.items[category].regionCategoryId+'"class="RCatclose_box">x</div> <h2>Apoorvi</h2><table><tr><td valign="top" width="90%"><div class="RCatcomment_box">'+jsonRegionCategories.items[category].regionCategoryName+':'+jsonRegionCategories.items[category].regionCategoryText+'</div></td><td valign="top" width="10%"><div regionCategoryId="'+jsonRegionCategory.items[category].regionCatgoryId+'"class="RCatsubmit_box">s</div></td></tr></table></div>');
	         	  }
    		  });
       return false;
  };	
function getPhotoComments(){
	  $.getJSON(
         'getPhotoComments.action' , {photoId:$('#canvas').attr('photoId')},
         	  function(jsonPhotoComments) {
        	  for (comment in jsonPhotoComments.items) {
        		  $('#photoComments').append('<div class="box"> <div photoCommentId="'+jsonPhotoComments.items[comment].photoCommentId+'" class="PCedit_box">e</div> <div photoCommentId="'+jsonPhotoComments.items[comment].photoCommentId+'"class="PCclose_box">x</div> <h2>Apoorvi</h2><table><tr><td valign="top" width="90%"><div class="PCcomment_box">'+jsonPhotoComments.items[comment].photoCommentText+'</div></td><td valign="top" width="10%"><div photoCommentId="'+jsonPhotoComments.items[comment].photoCommentId+'"class="PCsubmit_box">s</div></td></tr></table></div>');
	         	  }
         });
      return false;
  };	

  function getRegionComments(regionId){
	  $.getJSON(
         'getRegionComments.action' , {regionId:regionId},
         	  function(jsonRegionComments) {
        	 for (comment in jsonRegionComments.items) {
	         	  $('#regionComments').append('<div class="box"> <div regionCommentId="'+jsonRegionComments.items[comment].regionCommentId+'" class="RCedit_box">e</div><div regionCommentId="'+jsonRegionComments.items[comment].regionCommentId+'"class="RCclose_box">x</div> <h2>Apoorvi</h2><table><tr><td valign="top" width="90%"><div class="RCcomment_box">'+jsonRegionComments.items[comment].regionCommentText+'</div></td><td valign="top" width="10%"><div photoCommentId="'+jsonRegionComments.items[comment].regionCommentId+'"class="RCsubmit_box">s</div></td></tr></table></div>');
	         	  }
         });
      return false;
  };

  function getRegionCoordinates(){
	  $.getJSON(
	    	'getRegionCoordinates.action' , {photoId:$('#canvas').attr('photoId')},
	         	  function(jsonRegionCoordinates) {
	    		for (coordinate in jsonRegionCoordinates.items) {
	    		addBox(jsonRegionCoordinates.items[coordinate].regionX,jsonRegionCoordinates.items[coordinate].regionY,jsonRegionCoordinates.items[coordinate].width,jsonRegionCoordinates.items[coordinate].height,jsonRegionCoordinates.items[coordinate].regionId); 
	         	  }
	         });
	       return false;
	  };	
	  
	 
  function addPhotoCategory(photoCategoryName,photoCategortyText){
	 
		 $.getJSON(
	         	  'addPhotoCategory.action' , {photoId:$('#canvas').attr('photoId'),userId:1,photoCategoryName:photoCategoryName,photoCategoryText:photoCategortyText},
	         	  function(jsonPhotoCategories) {
	         		 alert(photoCategoryName+':'+photoCategortyText);
	         		for (category in jsonPhotoCategories.items) {
	         		$('#photoCategories').append('<div class="box"> <div categoryId="'+jsonPhotoCategories.items[category].photoCategoryId+'"class="PCatclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonPhotoCategories.items[category].photoCategoryName+':'+jsonPhotoCategories.items[category].photoCategoryText+'</p></div>');
	                 }
	         	  });
	         	 return false;
	         	};	
	      
	function addRegionCategory(regionCategoryName,regionCategoryText){
		$.getJSON(
	    	'addRegionCategory.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionCategoryName:regionCategoryName,regionCategortyText:regionCategoryText,},
	         	  function(jsonRegionCategories) {
	         	  for (category in jsonRegionCategories.items) {
	         	  $('#regionCategories').append('<div class="box"> <div categoryId="'+jsonRegionCategory.items[category].regionCategoryId+'"class="RCatclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionCategory.items[category].regionCategoryName+':'+jsonRegionCategory.items[category].regionCategoryText+'</p></div>');
	         	  }
	         });
	       return false;
	  };	
	function addPhotoComment(photoCommentText){
	alert(photoCommentText);
	      $.getJSON(
	         'addPhotoComment.action' , {photoId:$('#canvas').attr('photoId'),userId:1,photoCommentText:photoCommentText},
	         	  function(jsonPhotoComments) {
	        	  for (comment in jsonPhotoComments.items) {
	         	  $('#photoComments').append('<div class="box"> <div commentId="'+jsonPhotoComments.items[comment].photoCommentId+'"class="PCclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonPhotoComments.items[comment].photoCommentText+'</p></div>');
	         	  }
	         });
	      return false;
	  };	

	  function addRegionComments(regionCommentText){
	      $.getJSON(
	         'addRegionComment.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionCommentText:regionCommentText},
	         	  function(jsonRegionComments) {
	         	  for (comment in jsonRegionComments.items) {
	         	  $('#regionComments').append('<div class="box"> <div commentId="'+jsonRegionComments.items[comment].regionCommentId+'"class="RCclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionComments.items[comment].regionCommentText+'</p></div>');
	         	  }
	         });
	      return false;
	  };

	  function editPhotoCategory(photoCategoryName,photoCategortyText){
			 
			 $.getJSON(
		         	  'editPhotoCategory.action' , {photoId:$('#canvas').attr('photoId'),userId:1,photoCategoryName:photoCategoryName,photoCategoryText:photoCategortyText},
		         	  function(jsonPhotoCategories) {
		         	  });
		         	 return false;
		         	};	
		      
		function editRegionCategory(regionCategoryName,regionCategoryText){
			$.getJSON(
		    	'editRegionCategory.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionCategoryName:regionCategoryName,regionCategortyText:regionCategoryText,},
		         	  function(jsonRegionCategories) {
		         });
		       return false;
		  };	
		function editPhotoComment(photoCommentText){
		
		      $.getJSON(
		         'editPhotoComment.action' , {photoId:$('#canvas').attr('photoId'),userId:1,photoCommentText:photoCommentText},
		         	  function(jsonPhotoComments) {
		        	});
		      return false;
		  };	

		  function editRegionComments(regionCommentText){
		      $.getJSON(
		         'editRegionComment.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionCommentText:regionCommentText},
		         	  function(jsonRegionComments) {
		         	  
		         });
		      return false;
		  };
		  

	  function deleteRegionCategory(regionCategoryId){
			$.getJSON(
		    	'deleteRegionCategory.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionCategoryId:regionCategoryId},
		         	  function(jsonRegionCategories) {
		       	 });
		       return false;
		  };
		  
		 function deleteRegionComment(regionCommentId){
				$.getJSON(
			    	'deleteRegionComment.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionCommentId:regionCommentId},
			         	  function(jsonRegionCategories) {
			       	 });
			       return false;
			  };
	  
		function deletePhotoCategory(photoCategoryId){
					$.getJSON(
				    	'deletePhotoCategory.action' , {photoId:$('#canvas').attr('photoId'),userId:1,photoCategoryId:photoCategoryId},
				         	  function(jsonRegionCategories) {
				       	 });
				       return false;
				  };
		function deletePhotoComment(photoCommentId){
						$.getJSON(
					    	'deletePhotoComment.action' , {photoId:$('#canvas').attr('photoId'),userId:1,photoCommentId:photoCommentId},
					         	  function(jsonRegionCategories) {
					       	 });
					       return false;
					  };
	function parseJSON(){
		  alert('hie1');
		  jsonRegionComments={"items":[{"photoDesc":"my description","photoId":10,"photoIsdeleted":null,"photoLink":null,"photoMetadatalink":null,"photoName":null,"photoOption1":null,"photoOption2":null,"photoOption3":null,"photoOption4":null,"photoOption5":null,"photoTimestamp":null,"userId":0},{"photoDesc":"my description 2","photoId":20,"photoIsdeleted":null,"photoLink":null,"photoMetadatalink":null,"photoName":null,"photoOption1":null,"photoOption2":null,"photoOption3":null,"photoOption4":null,"photoOption5":null,"photoTimestamp":null,"userId":0}]};
	         	  for (comment in jsonRegionComments.items) {
	         		  $('#regionComments').append('<div class="box"> <div commentId="'+jsonRegionComments.items[comment].photoDesc+'"class="close_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionComments.items[comment].photoDesc+'</p></div>');
	         	  	}
	       
	      return false;
	  };
  
function addBox(x,y,w,h,regionId){
	var canvas = $('#canvas');
	 canvas.append('<div id="'+'regionx_' + x + '_y_' +y+'_w_' + w + '_h_' + h+'"></div>');
	 $('#'+'regionx_' + x + '_y_' +y+'_w_' + w + '_h_' + h).addClass('ui-boxer')
	     .css({ border: '1px solid red',
	             padding: '0.5em',
	             position: 'relative',
	            'z-index': 100,
	             left: parseInt(x), top: parseInt(y),
	             width: parseInt(w), height: parseInt(h)})
	     .append('<div id="'+regionId+'"class="Rclose_box">X</div> ')
		.click(function () {
			jQuery('#regionComments').html('');
			jQuery('#regionCategories div').html('');
				getRegionComments(regionId);
				getRegionCategories(regionId);


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
		var regionId=0;
		$.getJSON(
		    	'addPhotoRegion.action' , {photoId:$('#canvas').attr('photoId'),userId:1,regionX:offset.left,regionY:offset.top,width:ui.box.width(),height:ui.box.height()},
		         	  function(jsonRegionCoordinates) {
		    		alert('I came back');
		       	  for (coordinate in jsonRegionCoordinates.items) {
		       		regionId=  jsonRegionCoordinates.items[coordinate].regionId;
		       	  }
		         });
		
		ui.box.css({ border: '1px solid red', padding: '0.5em' });
		ui.box.attr('id', regionId);
		ui.box.append('<div id="'+regionId+'"class="Rclose_box">X</div> ');
		ui.box.click(function () {
			jQuery('#regionComments').html('');
			jQuery('#regionCategories div').html('');
				getRegionComments(regionId);
				getRegionCategories(regionId);

			
		});
		
	}
});
</script>
</form>

</body>
</html>