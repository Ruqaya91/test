<?php

session_start();

include("config.php");

 

$username = $_POST['username'];

$password = md5($_POST['password']); // Again, use password_hash() in production

 

$sql = "SELECT * FROM users WHERE username='$username' AND password='$password'";

$result = $conn->query($sql);

 

if ($result->num_rows === 1) {

    $_SESSION['username'] = $username;

    header("Location: dashboard.php");

} else {

    echo "Invalid login. <a href='login.php'>Try again</a>.";

}

?>
