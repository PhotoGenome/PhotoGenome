<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJAX calls using Jquery in Servlet</title>

        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
$(document).on('click','.close_box',function(){
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
    padding:15px;
    margin:10px;
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
                    var username=$('#txtRegionCategories').val();
                    alert(username);
                   $('#regionCategories').append('<div class="box"> <div class="close_box">X</div> <h2>Apoorvi</h2><p>'+username+'</p></div>');
                   $('#txtRegionCategories').text('');
                 
                });
                $('#submitRCom').click(function(event) {  
                    var username=$('#txtRegionComments').val();
                	    $('#regionComments').append('<br>'+username+'<br>'); 
                	    $('#txtRegionCategories').text('');
                });
                $('#cartId').click(function(event) {
                	 $.getJSON(
                  	  'giveMeJsonData.action' , {cartId:$('#cartId').attr('value')},
                  	  function(json) {
                  		alert('hello');
                  		alert(json);
                  		alert(json.shoppingCartId);
                  		$('#cartIdD').html(json.shoppingCartId);
                  	   $('#cartCreation').html(json.datetime);
                  	   itemsHtml = "<table>";
                  	   for (i in json.items) {
                  	    itemsHtml += '<tr>';
                  	    itemsHtml += '<td>' + json.items[i].id + '</td>';
                  	    itemsHtml += '<td>' + json.items[i].quantity + '</td>';
                  	    itemsHtml += '<td>' + json.items[i].price + '</td>';
                  	    itemsHtml += '</tr>';
                  	   }
                  	   itemsHtml += '</table>';
                  	   $('#cartItems').html(itemsHtml);
                  	  });
                  	 return false;
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

<input type="button" id="cartId" value="32233" />
<div id=”cartIdD”>JQuery will replace this text with the Cart Id returned by the json action</div>
<div id=”cartCreation”>JQuery will replace this text with the Cart creation date returned by the json action</div>
<div id=”cartItems”>Jquery wil replace this text with a HTML table containg all the items of the selected cart</div>
<input type="button" id="stop" value="Stop!" />
<input type="button" id="start" value="Start!" />


<script>

$(window).load(function () {
	addBox(100,100,50,50); 
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
         		for (category in jsonPhotoCategories) {
         		$('#photoCategories').append('<div class="box"> <div categoryId="'+category.categoryId+'"class="close_box">X</div> <h2>Apoorvi</h2><p>'+category.categorytext+'</p></div>');
                 }
         	  });
         	 return false;
         	};	
      
function getRegionCategories(){
	$.getJSON(
    	'getRegionCategories.action' , {photoId:$('#canvas').attr('photoId')},
         	  function(jsonRegionCategories) {
         	  for (category in jsonRegionCategories) {
         	  $('#regionCategories').append('<div class="box"> <div categoryId="'+category.categoryId+'"class="close_box">X</div> <h2>Apoorvi</h2><p>'+category.categorytext+'</p></div>');
         	  }
         });
       return false;
  };	
function getPhotoComments(){
	alert('I am here in JS');
	alert('PhotoId'+$('#canvas').attr('photoId'));
      $.getJSON(
         'getPhotoComments.action' , {photoId:$('#canvas').attr('photoId')},
         	  function(jsonPhotoComments) {
        	  alert('I have returnted from Server');
        	  alert(jsonPhotoComments);
         	  for (comment in jsonPhotoComments) {
         	  $('#photoComments').append('<div class="box"> <div commentId="'+comment.commentId+'"class="close_box">X</div> <h2>Apoorvi</h2><p>'+comment.commenttext+'</p></div>');
         	  }
         });
      return false;
  };	

  function getRegionComments(){
      $.getJSON(
         'getRegionComments.action' , {photoId:$('#canvas').attr('photoId')},
         	  function(jsonRegionComments) {
         	  for (comment in jsonRegionComments) {
         	  $('#regionComments').append('<div class="box"> <div commentId="'+comment.commentId+'"class="close_box">X</div> <h2>Apoorvi</h2><p>'+comment.commenttext+'</p></div>');
         	  }
         });
      return false;
  };
  
  function addBox(x,y,w,h){
	var canvas = $('#canvas');
	 canvas.append('<div id="'+'regionx_' + x + '_y_' +y+'_w_' + w + '_h_' + h+'"></div>');
	 $('#'+'regionx_' + x + '_y_' +y+'_w_' + w + '_h_' + h).addClass('ui-boxer')
	     .css({ border: '1px solid red',
	             padding: '0.5em',
	             position: 'relative',
	            'z-index': 100,
	             left: parseInt(x), top: parseInt(y),
	             width: parseInt(w), height: parseInt(h)})
		.click(function () {
			
		    $('#txtRegionCategories').attr('disabled', false).focus();
		    $('#regionCategories').text('name:HH');
		    $('#txtRegionComments').attr('disabled', false).focus();
		    $('#regionComments').text('Apoorvi: I like this building!');
		    
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
		ui.box.css({ border: '1px solid red', padding: '0.5em' });
		ui.box.attr('id', 'regionx_' + offset.left + '_y_' + offset.top+'_w_' + ui.box.width() + '_h_' + ui.box.height());
		 username='x:' + offset.left + ', y:' + offset.top+'w:' + ui.box.width() + ', h:' + ui.box.height();

		ui.box.click(function () {
			
		    $('#txtRegionCategories').attr('disabled', false).focus();
		    $('#regionCategories').text('name:HH');
		    $('#txtRegionComments').attr('disabled', false).focus();
		    $('#regionComments').text('Apoorvi: I like this building!');
		    
		});
		    username='x:' + offset.left + ', y:' + offset.top+'w:' + ui.box.width() + ', h:' + ui.box.height();
    	$.get('ActionServlet',{user:username},function(responseText) { 
            $('#welcometext').text(responseText);         
        });
	}
});
</script>
</form>

</body>
</html>