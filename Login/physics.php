<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['option1']) && isset($_POST['option2']) && isset($_POST['option3']) && isset($_POST['option4']) && isset($_POST['right_answer'])) {
    if ($db->dbConnect()) {
        if ($db->physics("physicsSub" , $_POST['username'],
         $_POST['option1'],
         $_POST['option2'],
         $_POST['option3'],
         $_POST['option4'],
         $_POST['right_answer'] )) {
            echo "Question Sign Up Success";
        } else echo "Question Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>