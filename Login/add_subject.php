


<?php 
require "DataBase.php";
$db = new DataBase();
if(isset($_POST['Subject'])&& isset($_POST['timer'])){
    if($db->dbConnect()){
        if($db->Subject("Subject",$_POST['Subject'],$_POST['timer'])){
            echo "done ";
            } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
    
?>