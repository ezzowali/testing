<?php
require "connect.php";

$username = $_POST["username"];
$Phy_marks = $_POST["Phy_marks"];


$sql = "update users set Phy_marks = '$Phy_marks' where username = '$username'";

if(mysqli_query($con,$sql))
{

echo " Succesfully update";

}
else
{
echo "Try again Later ..." .mysqli_error($con) ;

}
?>