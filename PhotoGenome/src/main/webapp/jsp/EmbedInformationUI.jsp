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
</script>
        <style>
  article, aside, figure, footer, header, hgroup, 
  menu, nav, section { display: block; }
  body{font: normal 16px/1.2em Arial, Helvetica, sans-serif;color:#444}
  *{margin:0;padding:0;}
  ul{list-style:none;}
  a{text-decoration:none;color:#666;}
  
 
  .box{
    position:relative;
    float:right;
    background:#eee;
    width:400px;
    padding:0px;
    margin:0px;
  }
  .PCclose_box{
    background:grey;
    color:#fff;
    padding:2px 5px;
    display:inline;
    position:absolute;
    right:15px;
    border-radius:3px;
    cursor:pointer;
  }
   .PCatclose_box{
    background:grey;
    color:#fff;
    padding:2px 5px;
    display:inline;
    position:absolute;
    right:15px;
    border-radius:3px;
    cursor:pointer;
  }
   .RCclose_box{
    background:grey;
    color:#fff;
    padding:2px 5px;
    display:inline;
    position:absolute;
    right:15px;
    border-radius:3px;
    cursor:pointer;
  }
   .RCatclose_box{
    background:grey;
    color:#fff;
    padding:2px 5px;
    display:inline;
    position:absolute;
    right:15px;
    border-radius:3px;
    cursor:pointer;
  }
   .Rclose_box{
    background:grey;
    color:#fff;
    padding:2px 5px;
    display:inline;
    position:absolute;
    right:15px;
    border-radius:3px;
    cursor:pointer;
  }
   .close_box{
    background:grey;
    color:#fff;
    padding:2px 5px;
    display:inline;
    position:absolute;
    right:15px;
    border-radius:3px;
    cursor:pointer;
  }
  
</style>
        <script>
            $(document).ready(function() {                        
                $('#submitRCat').click(function(event) {  
                    var regionCategories=$('#txtRegionCategories').val();
                    setRegionCategories(regionCategories);
                   $('#txtRegionCategories').text('');
                 
                });
                $('#submitRCom').click(function(event) {  
                    var regionComments=$('#txtRegionComments').val();
                    setRegionComments(regionComments);
                	$('#txtRegionCategories').text('');
                });
                $('#submitPCat').click(function(event) {  
                    var photoCategories=$('#txtPhotoCategories').val();
                    setPhotoCategories(photoCategories);
                    $('#txtPhotoCategories').text('');
                 
                });
                $('#submitPCom').click(function(event) {  
                    var photoComments=$('#txtPhotoComments').val();
                    setPhotoComments(photoComments);    
                    $('#txtPhotoCategories').text('');
                });
                
              	$('CategoryType').click( function( event )
                 {
                  alert($(this).val());
                  if($(this).val()==='')
                	  {
                	  
                	  } else {
                		  
                	  }
                 });
                		
                   
            });
                 
           
        </script>
        
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css" type="text/css" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
  <style type="text/css" media="screen">
  body { background-color: white; font: 16px Helvetica, Arial; color: black; }
  #canvas { height: 500px; background: white; }
  </style>

<style id="jsbin-css">

</style>
</head>
<body>
<form id="form1">
<h1>Coordinates	from server </h1>
<div id="welcometext"></div>
<h1>ui.boxer</h1>
<h2>Directions: click and drag in the white region to draw a box</h2>
<table width="100%" bordercolor="black">
<tr><td width="70%" bordercolor="black">
<div id="canvas" photoId="1" style="border:solid black; background-image:url('https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-ash3/942522_10151496380413710_188944828_n.jpg');"></div>
</td><td valign="top">
<table >
<tr><td >Current Region Categories</td></tr>
<tr><td><div id="regionCategories"></div> </td></tr>
<tr>
	<td><input type="radio" name="CategoryType" value="Predefined"> Predefined categories </td></tr>
	<tr> <td>
<select id="combobox">
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
		
	</select><textarea id="txtRegionCategoriesOnly" disabled=true rows="1" cols="20">
Enter values.  
</textarea></td></tr>
<tr><td> <input type="radio" name="CategoryType" value="Custom"> Custom <br> <textarea id="txtRegionCategories" disabled=true rows="1" cols="50">
Enter Categories and values here.  
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
<tr><td><div id="photoCategories">year:1929</div>
<textarea id="txtPhotoCategories" rows="1" cols="50">
Enter Categories Here. 
</textarea>
<input type="button" id="submitPCat" value="Comments Submit"/>
</td></tr>
<tr><td>Photo Comments</td></tr>
<tr><td><div id="photoComments">
Apoorvi: It was bright sunny day!!!</div>
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
	getPhotoComments(); 
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
 	         		$('#photoCategories').append('<div class="box"> <div categoryId="'+jsonPhotoCategory.items[category].photoCategoryId+'"class="PCatclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonPhotoCategory.items[category].photoCategoryText+'</p></div>');
 	                 }
         	  });
         	 return false;
         	};	
      
function getRegionCategories(regionId){
	$.getJSON(
    	'getRegionCategories.action' , {regionId:regionId},
         	  function(jsonRegionCategories) {
       	  for (category in jsonRegionCategories.items) {
         	  $('#regionCategories').append('<div class="box"> <div categoryId="'+jsonRegionCategory.items[category].regionCategoryId+'"class="RCatclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionCategory.items[category].regionCategoryText+'</p></div>');
         	  }
         });
       return false;
  };	
function getPhotoComments(){
	alert('I am here out');
      $.getJSON(
         'getPhotoComments.action' , {photoId:$('#canvas').attr('photoId')},
         	  function(jsonPhotoComments) {
        	 alert('I am here');
        	 alert(jsonPhotoComments);
        	  for (comment in jsonPhotoComments.items) {
        		  alert(jsonPhotoComments.items[comment]);
        		  $('#photoComments').append('<div class="box"> <div photoCommentId="'+jsonPhotoComments.items[comment].photoCommentId+'"class="PCclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonPhotoComments.items[comment].photoCommentText+'</p></div>');
	         	  }
         });
      return false;
  };	

  function getRegionComments(regionId){
      $.getJSON(
         'getRegionComments.action' , {regionId:regionId},
         	  function(jsonRegionComments) {
        	 for (comment in jsonRegionComments.items) {
	         	  $('#regionComments').append('<div class="box"> <div regionCommentId="'+jsonRegionComments.items[comment].regionCommentId+'"class="RCclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionComments.items[comment].regionCommentText+'</p></div>');
	         	  }
         });
      return false;
  };

  function getRegionCoordinates(){
		$.getJSON(
	    	'getRegionCoordinates.action' , {photoId:$('#canvas').attr('photoId')},
	         	  function(jsonRegionCoordinates) {
	       	  for (coordinate in jsonRegionCoordinates.items) {
	       		addBox(jsonRegionCoordinates.items[coordinate].x,jsonRegionCoordinates.items[coordinate].y,jsonRegionCoordinates.items[coordinate].width,jsonRegionCoordinates.items[coordinate].height,jsonRegionCoordinates.items[coordinate].id); 
	         	  }
	         });
	       return false;
	  };	
	  
	 
  function addPhotoCategories(photoCategortyText){
		 $.getJSON(
	         	  'addPhotoCategories.action' , {photoCategoryText:photoCategortyText},
	         	  function(jsonPhotoCategories) {
	         		for (category in jsonPhotoCategories.items) {
	         		$('#photoCategories').append('<div class="box"> <div categoryId="'+jsonPhotoCategory.items[category].photoCategoryId+'"class="PCatclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonPhotoCategory.items[category].photoCategoryText+'</p></div>');
	                 }
	         	  });
	         	 return false;
	         	};	
	      
	function addRegionCategories(regionCategoryText){
		$.getJSON(
	    	'addRegionCategories.action' , {regionCategortyText:regionCategoryText},
	         	  function(jsonRegionCategories) {
	         	  for (category in jsonRegionCategories.items) {
	         	  $('#regionCategories').append('<div class="box"> <div categoryId="'+jsonRegionCategory.items[category].regionCategoryId+'"class="RCatclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionCategory.items[category].regionCategoryText+'</p></div>');
	         	  }
	         });
	       return false;
	  };	
	function addPhotoComments(photoCommentText){
	
	      $.getJSON(
	         'addPhotoComments.action' , {photoCommentText:photoCommentText},
	         	  function(jsonPhotoComments) {
	        	  for (comment in jsonPhotoComments.items) {
	         	  $('#photoComments').append('<div class="box"> <div commentId="'+jsonPhotoComments.items[comment].photoCommentId+'"class="PCclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonPhotoComments.items[comment].photoCommentText+'</p></div>');
	         	  }
	         });
	      return false;
	  };	

	  function addRegionComments(regionCommentText){
	      $.getJSON(
	         'addRegionComments.action' , {regionCommentText:regionCommentText},
	         	  function(jsonRegionComments) {
	         	  for (comment in jsonRegionComments.items) {
	         	  $('#regionComments').append('<div class="box"> <div commentId="'+jsonRegionComments.items[comment].regionCommentId+'"class="RCclose_box">X</div> <h2>Apoorvi</h2><p>'+jsonRegionComments.items[comment].regionCommentText+'</p></div>');
	         	  }
	         });
	      return false;
	  };
	  
	  function deleteRegionCategories(regionId){
			$.getJSON(
		    	'deleteRegionCategories.action' , {regionId:regionId},
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
  function addBox(x,y,w,h,id){
	var canvas = $('#canvas');
	     
	canvas.append('<div></div>').addClass('ui-boxer')
	     .css({ border: '1px solid red',
	             padding: '0.5em',
	             position: 'relative',
	            'z-index': 100,
	             left: parseInt(x), top: parseInt(y),
	             width: parseInt(w), height: parseInt(h)})
	    .attr('id', id)
		.append('<div id="'+regionId+'"class="Rclose_box">X</div> ')
		.click(function () {
			
		    $('#txtRegionCategories').attr('disabled', false).focus();
		    $('#txtRegionComments').attr('disabled', false).focus();
		    
		});
			   return false;
	
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
		    	'addRegionCoordinates.action' , {x:offset.left,y:offset.top,width:ui.box.width(),height:ui.box.height()},
		         	  function(jsonRegionCoordinates) {
		       	  for (coordinate in jsonRegionCoordinates.items) {
		       		regionId=  jsonRegionCoordinates.items[coordinate].regionId;
		       	  }
		         });
		
		ui.box.css({ border: '1px solid red', padding: '0.5em' });
		ui.box.attr('id', regionId);
		ui.box.append('<div id="'+regionId+'"class="Rclose_box">X</div> ');
		ui.box.click(function () {
			
		    $('#txtRegionCategories').attr('disabled', false).focus();
		    $('#regionCategories').text('name:HH');
		    $('#txtRegionComments').attr('disabled', false).focus();
		    $('#regionComments').text('Apoorvi: I like this building!');
		    
		});
		
	}
});
</script>
</form>

</body>
</html>