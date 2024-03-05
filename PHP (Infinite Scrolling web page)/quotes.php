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

// Adding the file which connects with the database
include_once 'db.php';

// validating the parameter passed by AJAX call to be a int
$page = filter_input(INPUT_GET, 'page', FILTER_VALIDATE_INT);
$page = ($page === false) ? 1 : $page;

// setting the maximum number of quotes on a single screen
$per_page = 20;

$offset = ($page - 1) * $per_page;

// Creating the varible with the querry to extract required data from the database
// This code was given im the assignment instructions
$query = "SELECT quotes.quote_text, authors.author_name
          FROM quotes
          JOIN authors ON quotes.author_id = authors.author_id
          LIMIT :per_page
          OFFSET :offset ";

$stmt = $pdo->prepare($query);
$stmt->bindParam(':per_page', $per_page, PDO::PARAM_INT);
$stmt->bindParam(':offset', $offset, PDO::PARAM_INT);
$stmt->execute();

// Fetching quotes from the database
$quotes = $stmt->fetchAll(PDO::FETCH_ASSOC);

// creating Array for each quote with author and quote
$htmlQuotes = [];
foreach ($quotes as $quote) {
    $htmlQuotes[] = generateQuoteCard($quote['quote_text'], $quote['author_name']);
}

echo json_encode($htmlQuotes);

/**
 * This code creates the basic structure of the card.
 */

function generateQuoteCard($quoteText, $authorName) {
    // making the array with all bootstrap colors without black as the text will be black.
    $backgroundColors = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger', 'bg-warning', 'bg-info', 'bg-light'];

    // Randomly selecting a background color from the $backgroundColors array 
    $headerColor = $backgroundColors[array_rand($backgroundColors)];
    $bodyColor = $backgroundColors[array_rand($backgroundColors)];

    // using the htmlspecialchars() function to sanitize the content of database
    return '<div class="card mb-3 a4card w-100">
                <div class="card-header ' . $headerColor . '">' . htmlspecialchars($authorName) .  '</div>
                <div class="card-body d-flex align-items-center ' . $bodyColor . '">
                    <p class="card-text w-100">' . htmlspecialchars($quoteText) .  '</p>
                </div>
            </div>';
}
?>
