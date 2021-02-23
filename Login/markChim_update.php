<?php
require "connect.php";

$username = $_POST["username"];
$chim_marks = $_POST["chim_marks"];


$sql = "update users set chim_marks = '$chim_marks' where username = '$username'";

if(mysqli_query($con,$sql))
{

echo " Succesfully update";

}
else
{
echo "Try again Later ..." .mysqli_error($con) ;

}
?>