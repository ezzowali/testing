<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];
 $question  = $_POST['question'];
 
 require_once('dbConnect.php');
 
 $sql ="SELECT id FROM images ORDER BY id ASC";
 
 $res = mysqli_query($con,$sql);
 
 $id = 0;


 
 while($row = mysqli_fetch_array($res)){
 $id = $row['id'];
 }
 
 $path = "uploads/$id.jpeg";




 
 $actualpath = "http://192.168.64.2/Server/images/$path";
 
 $sql = "INSERT INTO images (image,question) VALUES ('$actualpath','$question ')";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }
 ?>