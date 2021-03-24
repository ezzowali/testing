<?php
require "DataBase.php";
$db = new DataBase();


if (isset($_POST['Subject']) && isset($_POST['Question']) && isset($_POST['option1']) 
&& isset($_POST['option2']) &&
 isset($_POST['option3']) 
&& isset($_POST['option4']) 
&& isset($_POST['right_answer']) && 
isset($_POST['image'])) {
    if ($db->dbConnect()) {



        if ($db->Qus_sub("Qus_sub" , $_POST['Subject'],$_POST['Question'],
         $_POST['option1'],
         $_POST['option2'],
         $_POST['option3'],
         $_POST['option4'],
         $_POST['right_answer'],
         $_POST['image']
         
         )) {

            echo "Question Sign Up Success";
        }
         else echo "Question Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>



