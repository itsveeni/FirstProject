<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table,td,th
{
  border:1px solid black;
}
td,th
{
  padding:10px;
}
table
{
  margin:auto;
}
</style>




<script>
	var xmlhttp; //globally declared variable for reference
    var buttonclicked;
	var msg;
	
	function senddata(button) {
		var cid = document.categoryform.cid.value; //we are reading the data and storing it in cid and name
		var name = document.categoryform.name.value;
		xmlhttp = new XMLHttpRequest(); //xmlhttprequest is a class and open is its method
		var javascriptobject = {
			"cid" : cid,
			"name" : name
		}; //we're converting this into json string that's why double quotes used fro keys
		
		buttonclicked=button.value;
		if(buttonclicked=="post")
		   {	
			    msg="data is saved/posted succesfully";
	         	xmlhttp.open("post","CategoryApi/savecategory");
		   }
		else
		   {
			   msg="data is updated succesfully";
			   xmlhttp.open("put","CategoryApi/updatecategory");
		   }
		xmlhttp.onload = displayResponse; //we don't need to call it we just need to tell it to call that's why no "()"
		var jsonstring = JSON.stringify(javascriptobject); //JSON (it's a predefined reference or object) here is just object reference inbuilt and stringify converts it into json string
		xmlhttp.setRequestHeader('Content-type', 'application/json');
		xmlhttp.send(jsonstring);
		alert("Data has been sent to api");

	}

	function displayResponse() 
	{
		document.getElementById("message").innerHTML=msg;
		alert(xmlhttp.responseText); //sending json string as response
	}
	
	function getdata(button)
	{
		var cid = document.categoryform.cid.value; //reading the data of cid text box
		xmlhttp = new XMLHttpRequest();
		buttonclicked=button.value;
		if(buttonclicked=="get")
		    {
			    msg="data is retrieved succesfully";
	        	xmlhttp.open("get", "CategoryApi/category/"+ cid);
		 
		    }
		else
			{
			    msg="data is deleted succesfully";
			    xmlhttp.open("delete","CategoryApi/deletecategory/"+ cid);
			}
		xmlhttp.onload = displayResponse2;
		xmlhttp.send();
	}
	
	function displayResponse2()
	{
		
		var jsonstring =  xmlhttp.responseText;
		var javascriptobject = JSON.parse(jsonstring); //parse method converts json string to javascript object
		var name = javascriptobject.name;
		
		/* if exception occurs at server, then server sends json string which has some key "message" having some recorrd 
		not found value which is defined and has value so it is not equal to undefined hence satisfies first condition
		*/
			
		if(javascriptobject.message!=undefined)
			{
			  document.categoryform.name.value="";
			  document.getElementById("message").innerHTML=javascriptobject.message;
			}
		
		/* but if exception did not raise then we have undefinned value in our message key
		so it gets equal to undefined and condition false
		*/
		else
			{
			  document.categoryform.name.value=name;
			  document.getElementById("message").innerHTML=msg;
			}
		
		
	}
	
	function getalldata()
	{
		xmlhttp = new XMLHttpRequest();
		xmlhttp.onload=displayResponse3;
		
		xmlhttp.open("get","CategoryApi/getallcategory");
		xmlhttp.send(); //this is to send our request
	}
	
	function displayResponse3()
	{
	   var table = document.getElementById("table1");
	   table.innerHTML=""; /*this is to clear the data in table whenever we add more new elements so that it won't save
	   repeated data again*/
	   //heading row
	   var headingrow = document.createElement("tr");
	   //number of headings(columns) in heading row
	   var cidth = document.createElement("th");
	   var nameth = document.createElement("th");
	   //value in it
	   var cidthvalue = document.createTextNode("Category ID");
	   var namethvalue = document.createTextNode("Category Names");
	   //appending names in rows
	   cidth.appendChild(cidthvalue);
	   nameth.appendChild(namethvalue);
	   //appending rows in heading row
	   headingrow.appendChild(cidth);
	   headingrow.appendChild(nameth);
	   
	   table.appendChild(headingrow);
	   
	   var data = xmlhttp.responseText;
	   alert(data);
	   data = data.substring(1,data.length-1);
	   var newstr="";
	   for(var i=0;i<data.length;i++)
		   {
		      var character = data[i];
		      if(character==',' && data[i+1]=='{')
		    	  {
		    	     newstr=newstr+"#";
		    	  }
		      else
		    	  {
		    	     newstr=newstr+character;
		    	  }
		    	  	   
		   }
	   alert(newstr);
	   var jsonstrings = newstr.split("#");
	   
	   for(var i=0;i<jsonstrings.length;i++)
		   {
		      var jsonrecord = jsonstrings[i];
		      var object = JSON.parse(jsonrecord);
		    
		      alert(object.cid+ " "+ object.name)
		      var row = document.createElement("tr");
		      
		      var cidtd = document.createElement("td");
		      var nametd = document.createElement("td");
		      
		      var cidvalue=document.createTextNode(object.cid);
		      var namevalue=document.createTextNode(object.name);
		      
		      cidtd.appendChild(cidvalue);
		      nametd.appendChild(namevalue);
		      
		      row.appendChild(cidtd);
		      row.appendChild(nametd);
		      
		      table.appendChild(row);
		      
		   }
	
	}
	
	
</script>

</head>
<body bgcolor=Orange>

	<form name=categoryform style="text-align: center; ">
		Category ID -<input type="text" name=cid> <br>
		<br> Category - <input type="text" name=name> <br>
		<br> <input type="button" value="post" onclick="senddata(this)">
		<input type="button" value="get" onclick="getdata(this)">
		<input type="button" value="put" onclick="senddata(this)">
		<input type="button" value="delete" onclick="getdata(this)">
		<input type="button" value="getall" onclick="getalldata()">
		<br><br> <input type="reset" value="reset">
		<br><br>
		<span id="message"> </span> <br><br>
		<table id="table1"></table>
		
	</form>

</body>
</html>