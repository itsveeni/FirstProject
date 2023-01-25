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

<script type="text/javascript">
    var xmlhttp;
    var msg;
    var buttonclicked;
    
    
    
    
    
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
    		   var pid = document.productmgmtform.pid.value; 	
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
    		           document.productmgmtform.name.value="";
    			       document.productmgmtform.price.value="";
    			  
    			       document.getElementById("message").innerHTML=javascriptobject.message;
    		     	}
    			else
    				{
    				   document.productmgmtform.name.value=name;
    				   document.productmgmtform.price.value=price;
    				   var productImage=document.getElementById("i1");
    				   
    				   productImage.src="/images/"+javascriptobject.pid+".jpg";
						productImage.width="50";
						productImage.height="50";
    				   
    				   document.getElementById("message").innerHTML=msg;
    				}
    		}
    	
    

    </script>

</head>
<body bgcolor = "khaki" style="text-align: center;" >

   <form name ="productmgmtform" enctype="multipart/form-data">
    Product ID -  <input type="text" name="pid" ><span id="errorMessage"> </span><br><br>
    Product Name - <input type="text" name="name" ><span id="errorMessage"> </span><br><br>
    Product Price - <input type="text" name="price" ><span id="errorMessage"> </span><br><br>
    Category ID - <input type="text" name="cid" ><span id="errorMessage"> </span><br><br>
    <label>Select Photo	</label>
			<input type="file" name="images"><br><br>
    
    <input type="submit" value="post" formmethod=post formaction="saveProduct">
    <input type="button" value="get" onclick="getdata()">
    <input type="button" value="getall" onclick="getalldata()">
    <img id="i1"><br><br><br><br>
	<table id="table1"></table>
    
    
    
   </form>
   
   <span id="message">${message}</span>
</body>
</html>