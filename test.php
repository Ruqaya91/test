<?php
// Registration process
$username = $_POST['username'];
$password = $_POST['password'];

// Hash the password
$hashedPassword = password_hash($password, PASSWORD_BCRYPT);

// Save to database (this is a sample; use prepared statements in production)
$conn = new mysqli("localhost", "root", "", "test");
$stmt = $conn->prepare("INSERT INTO users (username, password) VALUES (?, ?)");
$stmt->bind_param("ss", $username, $hashedPassword);
$stmt->execute();
echo "User registered successfully!";
?>
<?php
// Login process
$username = $_POST['username'];
$password = $_POST['password'];

$conn = new mysqli("localhost", "root", "", "test");
$stmt = $conn->prepare("SELECT password FROM users WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$stmt->bind_result($hashedPassword);
$stmt->fetch();

if (password_verify($password, $hashedPassword)) {
    echo "Login successful!";
} else {
    echo "Invalid credentials!";
}
?>
