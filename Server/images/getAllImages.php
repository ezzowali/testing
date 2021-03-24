<?php
 require_once('dbConnect.php');
 
 $sql = "SELECT * FROM `Qus_sub` ";
 
 $res = mysqli_query($con,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('url'=>$row['image'],
 'Question'=>$row['Question'], 'Subject'=>$row['Subject'],
  'option1'=>$row['option1'], 'option2'=>$row['option2'],
  'option3'=>$row['option3'], 'option4'=>$row['option4'],
  'right_answer'=>$row['right_answer']


));
 }
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);

 ?>

