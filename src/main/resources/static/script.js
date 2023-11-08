const ALGORITHM_API_URL = 'http://localhost:8081/api/v1/game/random-algorithm'
const codeDisplayElement = document.getElementById('codeDisplay')
const codeInputElement = document.getElementById('codeInput')
const timerElement = document.getElementById('timer')

codeInputElement.addEventListener('keydown', function (e) {
  if (e.key === 'Tab') {
    e.preventDefault();
    const start = this.selectionStart;
    const end = this.selectionEnd;

    // Insert two space characters at the cursor position instead of a Tab character
    this.value = this.value.substring(0, start) + '  ' + this.value.substring(end);
    
    // Move cursor position after the inserted two space characters
    this.selectionStart = this.selectionEnd = start + 2;
  }
});
codeInputElement.addEventListener('input', () => {
  const arrayCode = codeDisplayElement.querySelectorAll('span');
  const inputValue = codeInputElement.value;
  // Replace Tab characters with two space characters
  const sanitizedInput = inputValue.replace(/\t/g, '  ');

  const arrayValue = sanitizedInput.split('');

  let correct = true;

  arrayCode.forEach((characterSpan, index) => {
    const character = arrayValue[index];
    if (character == null) {
      characterSpan.classList.remove('correct');
      characterSpan.classList.remove('incorrect');
      correct = false;
    } else if (character === characterSpan.innerText) {
      characterSpan.classList.add('correct');
      characterSpan.classList.remove('incorrect');
    } else {
      characterSpan.classList.remove('correct');
      characterSpan.classList.add('incorrect');
      correct = false;
    }
  });

  if (correct) {
    renderNewCode();
  }
});

async function getRandomCode() {
  try {
    const response = await fetch(ALGORITHM_API_URL)
    const data = await response.json()
    return data[0].algorithm
  } catch (error) {
    console.error('Error fetching random algorithm:', error)
    return null
  }
}

async function renderNewCode() {
  try {
    const code = await getRandomCode();
    const formattedCode = formatCode(code); // Format the code here

    codeDisplayElement.innerHTML = ''; // Clear previous content
    formattedCode.split('').forEach(character => {
      const characterSpan = document.createElement('span');
      characterSpan.innerText = character;
      codeDisplayElement.appendChild(characterSpan);
    });

    codeInputElement.value = ''; // Clear input field
    startTimer();
  } catch (error) {
    console.error('Error rendering new code:', error);
    codeDisplayElement.innerText = 'Error fetching or formatting code. Please try again.';
  }
}
function formatCode(code) {
  const codeWithNewlines = code.replace(/\\n/g, '\n');
  return codeWithNewlines;
}


let startTime;
function startTimer() {
  timerElement.innerText = 0;
  startTime = new Date();

  // Clear the previous interval if it exists
  if (timerInterval) {
    clearInterval(timerInterval);
  }

  // Start a new interval
  timerInterval = setInterval(() => {
    timerElement.innerText = getTimerTime();
  }, 1000);
}

function getTimerTime() {
  return Math.floor((new Date() - startTime) / 1000);
}

let timerInterval;
renderNewCode()
