<?php
$host = "localhost";
$user = "root";
$password =  "";
$db = "test2";


$con = mysqli_connect($host,$user,$password,$db);

if(!$con)
{
    die("Error in connection " .mysqli_connect_error());
}
else
{
echo" " ;

}

?>