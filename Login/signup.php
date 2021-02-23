<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['Phy_marks']) && isset($_POST['chim_marks']) ) {
    if ($db->dbConnect()) {
        if ($db->signUp("users" , $_POST['username'], $_POST['password'] , $_POST['Phy_marks'], $_POST['chim_marks'] )) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>


