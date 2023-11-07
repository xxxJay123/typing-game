const codeSnippetElement = document.getElementById('codeSnippet');
const userInput = document.getElementById('userInput');

// Dummy code examples (replace this with data fetched from your API)
let codeExamples = [];

async function fetchCodeExamples() {
    try {
        const response = await fetch('/api/v1/game/words/BUSINESS_VOCABULARY'); // Replace this URL with your API endpoint
        if (response.ok) {
            const data = await response.json();
            // Assuming the API response contains a property 'word' for each code example
            codeExamples = data.map(item => item.word);
            displayCodeSnippet();
        } else {
            console.error('Error fetching code examples:', response.statusText);
        }
    } catch (error) {
        console.error('Error fetching code examples:', error);
    }
}
function displayCodeSnippet() {
    // Get a random code example from the fetched data
    const randomIndex = Math.floor(Math.random() * codeExamples.length);
    const codeSnippet = codeExamples[randomIndex];

    // Display the code snippet in your UI element (assuming you have an element with id 'codeSnippet')
    const codeSnippetElement = document.getElementById('codeSnippet');
    codeSnippetElement.textContent = codeSnippet;
}

function checkCode() {
    const userCode = userInput.value.trim();
    const expectedCode = codeSnippetElement.textContent.trim();

    // Remove leading/trailing whitespace and newlines for accurate comparison
    const cleanedUserCode = userCode.replace(/^\s+|\s+$/g, '');
    const cleanedExpectedCode = expectedCode.replace(/^\s+|\s+$/g, '');

    if (cleanedUserCode === cleanedExpectedCode) {
        // User's code is correct
        alert('Your code is correct!');
        // Display a new code snippet
        displayCodeSnippet();
    } else {
        // User's code is incorrect
        alert('Your code is incorrect. Please try again.');
    }

    // Clear user input for the next attempt (optional)
    userInput.value = '';
}

// Initial setup
displayCodeSnippet();
