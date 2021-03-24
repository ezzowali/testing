<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $Subject = $_POST['Subject'];
 $Question  = $_POST['Question'];
 $option1 = $_POST['option1'];
 $option2  = $_POST['option2'];
 $option3 = $_POST['option3'];
 $option4  = $_POST['option4'];
 $right_answer = $_POST['right_answer'];
$image = $_POST['image'];



 

 require_once('dbConnect.php');
 
 $sql ="SELECT id FROM Qus_sub ORDER BY id ASC";
 
 $res = mysqli_query($con,$sql);
 
 $id = 0;


 
 while($row = mysqli_fetch_array($res)){
 $id = $row['id'];
 }
 
 $path = "uploads/$id.jpeg";

 $actualpath = "http://192.168.64.2/Server/images/$path";
 
 $sql = "INSERT INTO Qus_sub (image,Question,option1,option2,option3,option4,right_answer,Subject) 
 VALUES ('$actualpath','$Question','
 $option1','$option2','$option3','$option4','$right_answer','$Subject ')";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }
 ?>