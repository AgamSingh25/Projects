<?php

/**
 * I Agam Singh, 000903500, certify that this material is my original work.
 * No other person's work has been used without suitable acknowledgment and I have not made
 * my work available to anyone else.
 */

/**
 * @author Agam Singh
 * @package COMP 10260 Assignment 4
 * 
 * @version 2023.35.0
 */

$host = 'localhost';
$dbname = 'sa000903500';
$username = 'sa000903500';
$password = 'Sa_20040525';

//trying to connect to database with the provided credentials
try {
    // Createing a PDO instance
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $username, $password);

    // Setting the PDO error mode to exception
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} 

// The catch blocks prints the error on the screen if the connection is failed
catch (PDOException $e) {
    die('Connection failed: ' . $e->getMessage());
}
?>
