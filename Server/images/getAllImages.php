<?php
 require_once('dbConnect.php');
 
 $sql = "SELECT * FROM `images` ";
 
 $res = mysqli_query($con,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('url'=>$row['image'],
 'question'=>$row['question']
));
 }
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);

 ?>

