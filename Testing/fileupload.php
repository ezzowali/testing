<?php
$conn=mysqli_connect("localhost","root","","test2");
// mysqli_select_db($conn,"android_db");

    
	   $name=$_POST['t1'];
	   $design=$_POST['t2'];	   
	   $img=$_POST['upload'];

	   

                   $filename="http://192.168.64.2/images/IMG".rand().".jpg";
	   file_put_contents("images/".$filename,base64_decode($img));

			$qry="INSERT INTO `tbl_staff` (`id`, `name`, `desig`, `image`)
			      VALUES (NULL, '$name', '$design', '$filename')";

			$res=mysqli_query($conn,$qry);
			
			if($res==true)
			 echo "File Uploaded Successfully";
			else
			 echo "Could not upload File";
?>
