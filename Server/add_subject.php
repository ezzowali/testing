


<?php 
require "DataBase.php";
$db = new DataBase();
if(isset($_POST['Subject'])&& isset($_POST['timer'])&& isset($_POST['first_date'])&& isset($_POST['second_date'])
&& isset($_POST['first_time'])&& isset($_POST['second_time'])){
    if($db->dbConnect()){
        if($db->Subject("Subject",
        $_POST['Subject'],
        $_POST['timer'], 
        $_POST['first_date'],
        $_POST['second_date']
        ,$_POST['first_time'],
        $_POST['second_time']) ){
            echo "done ";
            } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
    
?>

