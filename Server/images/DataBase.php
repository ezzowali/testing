<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->
        sql = "select * from " . $table . " where username = '" . $username . "'AND password='" .$password.  "'";

        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $username &&  $dbpassword == $password) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $username, $password)
    {

        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
       


        $this->sql =
            "INSERT INTO " . $table . " (username, password) VALUES ('"  . "$username" . "','" . "$password" ."')";

        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }


    function physics($table, $username, $option1,$option2,$option3,$option4,$right_answer)
    {

        $username = $this->prepareData($username);
        $option1 = $this->prepareData($option1);
        $option2 = $this->prepareData($option2);
        $option3 = $this->prepareData($option3);
        $option4 = $this->prepareData($option4);
        $right_answer= $this->prepareData($right_answer);

        $this->sql =
            "INSERT INTO " . $table . " (username, option1,option2,option3,option4,right_answer) VALUES ('"  . "$username" . "','" . "$option1" . "','" . "$option2" . "','" . "$option3" . "','" . "$option4" . "','" . "$right_answer" . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function chimistry($table, $username, $option1,$option2,$option3,$option4,$right_answer)
    {

        $username = $this->prepareData($username);
        $option1 = $this->prepareData($option1);
        $option2 = $this->prepareData($option2);
        $option3 = $this->prepareData($option3);
        $option4 = $this->prepareData($option4);
        $right_answer= $this->prepareData($right_answer);

        $this->sql =
            "INSERT INTO " . $table . " (username, option1,option2,option3,option4,right_answer) VALUES ('"  . "$username" . "','" . "$option1" . "','" . "$option2" . "','" . "$option3" . "','" . "$option4" . "','" . "$right_answer" . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }


    function Subject($table, $Subject,$timer,$first_date,$second_date,$first_time,$second_time)
    {



        $Subject = $this->prepareData($Subject);

        $timer = $this->prepareData($timer);

        $first_date = $this->prepareData($first_date);

        $second_date = $this->prepareData($second_date);

        $first_time = $this->prepareData($first_time);

        $second_time = $this->prepareData($second_time);
    

        $this->sql =
            "INSERT INTO " . $table . " (Subject,timer,first_date,second_date,first_time,second_time) VALUES ('"  . "$Subject" ."','" . "$timer" . "','" . "$first_date" . "','" . "$second_date" . "','" . "$first_time" . "','" . "$second_time" . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }




    function Qus_sub($table,$Subject, $Question, $option1,$option2,$option3,$option4,$right_answer,$image)
    {

        $Question = $this->prepareData($Question);
        $option1 = $this->prepareData($option1);
        $option2 = $this->prepareData($option2);
        $option3 = $this->prepareData($option3);
        $option4 = $this->prepareData($option4);
        $right_answer= $this->prepareData($right_answer);
        $Subject=$this->prepareData($Subject);
        $image=$this->prepareData($image);

        
    

        $this->sql =
            "INSERT INTO " . $table . " (Subject,Question, option1,option2,option3,option4,right_answer,image) VALUES ('" . "$Subject" . "','" . "$Question" . "','" . "$option1" 
            . "','" . "$option2" . "','" . "$option3" . "','" . "$option4" . "','" 
            . "$right_answer" . "','" . "$image" . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }


    


    function marks($table, $username, $subjectName,$marks)
    {

        $username = $this->prepareData($username);
        $subjectName = $this->prepareData($subjectName);
        $marks = $this->prepareData($marks);
  

        $this->sql =
            "INSERT INTO " . $table . " (username, subjectName,marks) VALUES ('"  . "$username" . "','" . "$subjectName" . "','" . "$marks" .  "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }




    









}



?>