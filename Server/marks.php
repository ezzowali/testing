<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['subjectName']) && isset($_POST['marks']) ) {
    if ($db->dbConnect()) {
        if ($db->marks("marks" , $_POST['username'], $_POST['subjectName'] , $_POST['marks'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>


