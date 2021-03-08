<?php
 $conn=mysqli_connect("localhost","root","","test2");
 
 $sql = "SELECT * FROM `tbl_staff` ";
 
 $res = mysqli_query($conn,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('url'=>$row['image']));
 }
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($conn);

 ?>



 