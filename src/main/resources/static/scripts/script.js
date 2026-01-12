const colors = ['#FF007A', '#00D4FF', '#FFD700', '#00FF87', '#9D00FF'];
let score = 0;
const scoreDisplay = document.getElementById('count');

function createBubble() {
    const bubble = document.createElement('div');
    const size = Math.random() * 60 + 30 + 'px';
    const color = colors[Math.floor(Math.random() * colors.length)];

    bubble.className = 'bubble';
    bubble.style.width = size;
    bubble.style.height = size;
    bubble.style.left = Math.random() * 100 + 'vw';
    bubble.style.borderColor = color;
    bubble.style.boxShadow = `inset 0 0 15px ${color}`;

    // Randomize speed between 3 and 7 seconds
    const duration = Math.random() * 4 + 3;
    bubble.style.animationDuration = duration + 's';

    bubble.addEventListener('mousedown', () => {
        score++;
        scoreDisplay.innerText = score;
        bubble.classList.add('pop');
        setTimeout(() => bubble.remove(), 300);
    });

    document.body.appendChild(bubble);

    // Clean up DOM after bubble leaves screen
    setTimeout(() => {
        if(bubble.parentElement) bubble.remove();
    }, duration * 1000);
}

// Spawn a new bubble every 400ms
setInterval(createBubble, 400);