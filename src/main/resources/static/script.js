function selectTopic(topic) {
  fetch(`/api/v1/game/words/${topic}`)
  .then(response => {
      if (response.ok) {
          return response.json();
      }
      throw new Error('Network response was not ok.');
  })
  .then(data => {
      // Update the 'word' span element with the fetched word
      document.getElementById('word').textContent = data.word;
  })
  .catch(error => console.error('Error:', error));
}

function checkWord() {
  const userInput = document.getElementById('userInput').value.trim();
  fetch('/api/v1/game/words/check', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({ userInput: userInput })
  })
  .then(response => {
      if (response.ok) {
          return response.json();
      }
      throw new Error('Network response was not ok.');
  })
  .then(data => {
      if (data.correct) {
          // Logic for correct word
          updateScore(); // Implement this function to update the score
          selectTopic(currentTopic); // Fetch a new word
      } else {
          // Logic for incorrect word
          console.log('Incorrect word. Try again.');
      }
  })
  .catch(error => console.error('Error:', error));
}
