<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table,td,th
{
  border:1px solid black;
}ProductsApi
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
  
    var xmlhttp;
    var msg;
    function getalldata()
    {
    	xmlhttp = new XMLHttpRequest();
    	xmlhttp.onload=display;
    	xmlhttp.open("get","ProductsApi/allproducts");
    	xmlhttp.send();
    	
    }
    
    function display()
    {
    	var table = document.getElementById("table1");
    	table.innerHTML="";
    	
    	var headingrow = document.createElement("tr");
    	
    	var pidth = document.createElement("th");
    	var nameth = document.createElement("th");
    	var priceth = document.createElement("th");
    	var imageth=document.createElement("th");
    	
    	var pidvalue = document.createTextNode("Product ID");
    	var namevalue = document.createTextNode("Product Name");
    	var pricevalue = document.createTextNode("Product Price");
    	var imgvalue=document.createTextNode("product Image");
    	
    	pidth.appendChild(pidvalue);
    	nameth.appendChild(namevalue);
    	priceth.appendChild(pricevalue);
    	imageth.appendChild(imgvalue);
    	
    	headingrow.appendChild(pidth);
    	headingrow.appendChild(nameth);
    	headingrow.appendChild(priceth);
    	headingrow.appendChild(imageth);
    	
    	table.appendChild(headingrow);
    	
    	var data=xmlhttp.responseText;
    	alert(data);
    	data = data.substring(1,data.length-1);
    	alert(data);
    	
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
    			      
    			      var row = document.createElement("tr");
    			      
    			      var pidtd = document.createElement("td");
    			      var nametd = document.createElement("td");
    			      var pricetd = document.createElement("td");
    			      var imagetd=document.createElement("td");
    			      
    			      var pidtdvalue = document.createTextNode(object.pid);
    			      var nametdvalue = document.createTextNode(object.name);
    			      var pricetdvalue = document.createTextNode(object.price);
    			      var productimage=document.createElement("img");
    			      
    			      productimage.src="/images/"+object.pid+".jpg";
    			      
    			      productimage.width="40";
				      productimage.height="40";
    			      
    			      pidtd.appendChild(pidtdvalue);
    			      nametd.appendChild(nametdvalue);
    			      pricetd.appendChild(pricetdvalue);
    			      imagetd.appendChild(productimage);
    			      
    			      row.appendChild(pidtd);
    			      row.appendChild(nametd);
    			      row.appendChild(pricetd);
    			      row.appendChild(imagetd);
    			      
    			      table.appendChild(row);
    			   }
    		}   
    		function getdata()
    		{
    		   var pid = document.productform.pid.value; 	
    		   xmlhttp = new XMLHttpRequest();
    		   xmlhttp.open("get", "ProductsApi/getproducts/"+ pid);
    		   msg="data is retrieved succesfully";
    		   xmlhttp.onload = display2;
    		   xmlhttp.send();
    		}
    		
    		function display2()
    		{
    			var jsonstring =  xmlhttp.responseText;
    			var javascriptobject = JSON.parse(jsonstring);
    			var name = javascriptobject.name;
    			var price = javascriptobject.price;
    			
    			if(javascriptobject.message!=undefined)
    		    	{
    		           document.productform.name.value="";
    			       document.productform.price.value="";
    			  
    			       document.getElementById("message").innerHTML=javascriptobject.message;
    		     	}
    			else
    				{
    				   document.productform.name.value=name;
    				   document.productform.price.value=price;
    				   var productImage=document.getElementById("i1");
    				   
    				   productImage.src="/images/"+javascriptobject.pid+".jpg";
						productImage.width="100";
						productImage.height="100";
    				   
    				   document.getElementById("message").innerHTML=msg;
    				}
    		}
    	
    
    	
    	


</script>
</head>
<body bgcolor="salmon">

 welcome ${username}

<%!
  String imageURL=""; //empty
%>

<%
	imageURL="/images/"+ request.getAttribute("username") +".jpg"; //from browser and saving with username extension jpg
%>

<br><br>
<img src=<%=imageURL %> height=100px width=100px style="text-align: center; "> <!--display -->
<br><br>


<form name=productform style="text-align: center; ">
		Product ID -<input type="text" name=pid> <br><br> 
		Product Name - <input type="text" name=name> <br><br> 
		Product Price - <input type="text" name=price><br><br>
		<input type="button" value="get" onclick="getdata()">
		<input type="button" value="getall" onclick="getalldata()">
		<br><br> <input type="reset" value=reset> <br><br>
		<span id="message"> </span> <br><br>
		<img id="i1"><br><br><br><br>
		<table id="table1"></table>
		
	</form>
</body>
</html>