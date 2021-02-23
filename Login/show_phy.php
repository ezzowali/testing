<?php
$conn=mysqli_connect("localhost","root","","test2");
mysqli_select_db($conn,"test2");


$qry="select * from physicsSub" ;
$raw=mysqli_query($conn,$qry);
while ($res = mysqli_fetch_array($raw)) {

    $data[]=$res;
}
print(json_encode($data));
?>